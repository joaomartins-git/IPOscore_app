import pandas as pd
from services.data_manager.utils import Utils
import os.path as path
from pathlib import Path


class DataClean:

    @staticmethod
    def clean_data():
        dataset_name = "ipodata.xlsx"
        sheet_name = "Folha1"

        directory = Path(__file__).resolve().parents[2]
        data = pd.read_excel(path.join(directory, "data", dataset_name), sheet_name=sheet_name, dtype=str, header=1)

        data = Utils.rename_columns(data)
        data = Utils.change_to_default_na(data)
        data = Utils.remove_numerical_mistakes(data)

        data = pd.concat(
            [
                data,
                Utils.create_dataframe(data, "Intervenções_ICD10", "ICD_", False),
                Utils.create_dataframe(data, "classificação ACS complicações específicas", "ACS_CE_", False),
                Utils.create_dataframe(data, "ACS_procedimento", "ACS_", True)
            ],
            axis=1,
            sort=False
        )

        index = 0
        for value in data["% Sobrevida estimada em 10 anos"]:
            if pd.isna(value):
                index += 1
                continue
            data.at[index, "% Sobrevida estimada em 10 anos"] = float(value) * 100

        data.to_csv(path.abspath(path.join(directory, "data", "data.csv")), index=False)


DataClean.clean_data()
