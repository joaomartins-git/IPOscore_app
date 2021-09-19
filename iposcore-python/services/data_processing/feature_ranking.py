import numpy as np
import pandas as pd
from sklearn.feature_selection import mutual_info_classif, mutual_info_regression, chi2, f_classif, f_regression
from sklearn.feature_extraction.text import CountVectorizer
from data.global_variables import GlobalVariables
import json


# Sorts values, and corresponding names, from highest value to lowest value
def sort(colunas, valores):
    novas_colunas = []
    novos_valores = []
    while len(valores) != 0:
        index = 0
        curr_maximo = 0
        curr_index = 0
        for valor in valores:
            if valor > curr_maximo:
                curr_maximo = valor
                curr_index = index
            index += 1
        novas_colunas.append(colunas[curr_index])
        novos_valores.append(curr_maximo)
        del colunas[curr_index]
        del valores[curr_index]
    return [novas_colunas, novos_valores]


# Filter out values of no interest
def retrieveValues(array, index):
    colunas = []
    valores = []
    for value in array:
        if round(value[index], 5) == 0.00000 or pd.isna(value[index]):
            continue
        # Name of column is always last place of array
        colunas.append(value[len(value)-1])
        valores.append(round(value[index], 7))
    aux = sort(colunas, valores)
    aux[0] = aux[0][::-1]
    aux[1] = aux[1][::-1]
    return aux


# Run all score functions with given column and class label
def classifiers_values(array, column, labels, label):
    auxiliar = []
    for value in labels[label]:
        auxiliar.append(float(value))

    values_chi2 = chi2(
        labels[column].to_frame(name=column),
        auxiliar
    )
    values_f_regression = f_regression(
        pd.to_numeric(labels[column]).to_frame(name=column),
        auxiliar
    )
    values_f_classif = f_classif(
        labels[column].to_frame(name=column),
        auxiliar
    )

    array.append(
        [
            values_chi2[1][0],
            values_f_regression[1][0],
            values_f_classif[1][0],
            column
         ]
    )


class NpEncoder(json.JSONEncoder):
    def default(self, obj):
        if isinstance(obj, np.integer):
            return int(obj)
        elif isinstance(obj, np.floating):
            return float(obj)
        elif isinstance(obj, np.ndarray):
            return obj.tolist()
        else:
            return super(NpEncoder, self).default(obj)


class FeatureRanking:

    @staticmethod
    def feature_ranking(data, columns, class_label):
        # No need to display warning
        np.seterr(divide="ignore", invalid="ignore")
        # All possible data types
        column_types = ["Binary", "Categorical", "Numerical_Discrete", "Numerical_Contiguous"]
        # All statistics of score functions used
        classifiers = [
            "chi2_p-value",
            "f_regression_p-value",
            "ANOVA_p-value",
        ]
        # Initialize json data structure
        final_data = {}
        for type in column_types:
            final_data[type] = {}
            for cls in classifiers:
                final_data[type][cls] = {}
                for label in class_label:
                    final_data[type][cls][label] = []

        for label in class_label:
            # Array for each type of data
            binary_m = []
            categorical_m = []
            numerical_discrete_m = []
            numerical_continuous_m = []
            #text = []
            class_data = data[label]
            # Array of all columns to consider
            joined_array = []
            for array in columns:
                joined_array = joined_array + array

            for column in joined_array:
                true_label = class_data.__deepcopy__()
                pred_label = data[column]

                labels = pd.concat([true_label, pred_label], axis=1, sort=False)
                labels = labels.dropna()
                labels = labels.reset_index(drop=True)

                if (column in GlobalVariables.DatasetColumns.binary) and not(column in GlobalVariables.DatasetColumns.ignored_feature_in_ranking):
                    classifiers_values(binary_m, column, labels, label)
                elif (column in GlobalVariables.DatasetColumns.categorical) and not(column in GlobalVariables.DatasetColumns.ignored_feature_in_ranking):
                    classifiers_values(categorical_m, column, labels, label)
                elif (column in GlobalVariables.DatasetColumns.numerical_discrete) and not(column in GlobalVariables.DatasetColumns.ignored_feature_in_ranking):
                    classifiers_values(numerical_discrete_m, column, labels, label)
                elif (column in GlobalVariables.DatasetColumns.numerical_continuous) and not(column in GlobalVariables.DatasetColumns.ignored_feature_in_ranking):
                    classifiers_values(numerical_continuous_m, column, labels, label)
                #elif column in GlobalVariables.DatasetColumns.text:
                #    cv = CountVectorizer(max_df=0.95, min_df=2, max_features=10000)
                #    X_vec = cv.fit_transform(labels[column])
                #    dictionary = dict(
                #        zip(
                #            cv.get_feature_names(),
                #            mutual_info_classif(
                #                X_vec,
                #                labels[label],
                #                discrete_features=True
                #            )
                #        )
                #    )
                #    colunas = []
                #    valores = []
                #    for col, val in dictionary.items():
                #        if val == 0:
                #            continue
                #        colunas.append(col)
                #        valores.append(val)
                #    aux = sort(colunas, valores)
                #    aux[0] = aux[0][:10]
                #    aux[1] = aux[1][:10]
                #    text.append(aux)

            arrays = [binary_m, categorical_m, numerical_discrete_m, numerical_continuous_m]
            # Fill json with score function statistical data
            for index in range(0, len(arrays)):
                for classif_index in range(0, len(classifiers)):
                    aux = retrieveValues(
                        arrays[index],
                        classif_index
                    )
                    temp = []
                    for i in range(0, len(aux[0])):
                        x = {
                            "column_name": aux[0][i],
                            "column_value": aux[1][i]
                        }
                        temp.append(x)
                    final_data[column_types[index]][classifiers[classif_index]][label] = temp

        return json.dumps(final_data, cls=NpEncoder)
