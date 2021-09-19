
class GlobalVariables:

    class DatasetColumns:
        class_label = ["Clavien_Dindo_classification", "post_surgical_complication"]

        numerical_continuous = ["UCI_days",
                                "NAS_totalpoints",
                                "NAS_pointsperday",
                                "P_Possum_Morbidity_Percentage",
                                "P_Possum_Mortality_Percentage",
                                "ACS_weight",
                                "Serious_complications_percentage",
                                "Any_complications_percentage",
                                "Pneumonia_percentage",
                                "Cardiac_complications_percentage",
                                "Surgical_infection_percentage",
                                "ITU_percentage",
                                "Venous_thromboembolism_percentage",
                                "Kidney_failure_percentage",
                                "Ileus_percentage",
                                "Anastomotic_leakage_percentage",
                                "Readmission_percentage",
                                "Reoperation_percentage",
                                "Death_percentage",
                                "Discharge_To_Nursing_or_Rehab_Facility_percentage",
                                "Average_risk_serious_complications",
                                "Average_risk_any_complications",
                                "Average_risk_pneumonia",
                                "Average_risk_cardiac_complications",
                                "Average_risk_surgical_infection",
                                "Average_risk_ITU",
                                "Average_risk_venous_thromboembolism",
                                "Average_risk_kidney_failure",
                                "Average_risk_ileus",
                                "Average_risk_Anastomotic_leakage",
                                "Average_risk_readmission",
                                "Average_risk_reoperation",
                                "Average_risk_death",
                                "Average_risk_discharge_nursing_rehab",
                                "ACS_days_prevision"
                                ] #35

        categorical = ["Anesthesia_request_type",
                       "Provenace",
                       "Admission_Motive",
                       "Surgery_Type",
                       "Specialty_COD",
                       "destiny_after_Uci",
                       "ASA",
                       "PP_age",
                       "PP_cardiac",
                       "PP_respiratory",
                       "PP_ECG",
                       "PP_systolic_blood_pressure",
                       "PP_artirial_pulse",
                       "PP_hemoglobin",
                       "PP_leukocytes",
                       "PP_urea",
                       "PP_sodium",
                       "PP_potassium",
                       "PP_Glasgow_scale",
                       "PP_operation_type",
                       "PP_number_of_procedures",
                       "PP_blood_loss",
                       "PP_peritoneal_contamination",
                       "PP_state_of_malignancy",
                       "PP_CEPOD_grading_operation",
                       "ACS_age",
                       "ACS_functional_state",
                       "ACS_ASA",
                       "ACS_systemic_sepsis",
                       "ACS_diabetes",
                       "ACS_dyspnea",
                       "ARISCAT_age",
                       "ARISCAT_SpO2",
                       "ARISCAT_surgical_incision",
                       "ARISCAT_surgery_duration",
                       "ARISCAT_Score",
                       "CHARLSON_age",
                       "CHARLSON_diabetes_mellitus",
                       "CHARLSON_liver_disease",
                       "CHARLSON_malignancy_solid_tumor",
                       "complication_principal_COD",
                       "ACS_classification_general_complications",
                       "destiny_after_IPO_Discharge",
                       "death_time_elapsed_after_surgerydate"
                       ]#44

        numerical_discrete = ["Age",
                              "IPOP_days",
                              "P_Possum_Physiological_Score",
                              "P_Possum_Surgical_Severity_Score",
                              "ACS_height",
                              "ARISCAT_Total_points",
                              "POINTS_Charlson_Comorbidity_Index",
                              "CHARLSON_Estimated_10year_survival_percentage"
                              ]#8

        binary = ["FirstSurgery_in_IPO",
                  "Preoperative_QT",
                  "Readmission_UCI",
                  "Gender",
                  "ACS_gender",
                  "ACS_emergency",
                  "ACS_steroids",
                  "ACS_ascites",
                  "ACS_ventilator_dependent",
                  "ACS_disseminated_cancer",
                  "ACS_hypertension",
                  "ACS_ICC",
                  "ACS_smoker",
                  "ACS_DPOC",
                  "ACS_dialysis",
                  "ACS_acute_renal_failure",
                  "ARISCAT_respiratory_infection_last_month",
                  "ARISCAT_preoperative_anemia",
                  "ARISCAT_emerging_Procedure",
                  "CHARLSON_AIDS",
                  "CHARLSON_chronic_kidney_disease",
                  "CHARLSON_heart_failure",
                  "CHARLSON_myocardial_infarction",
                  "CHARLSON_DPOC",
                  "CHARLSON_peripheral_vascular_disease",
                  "CHARLSON_AVC",
                  "CHARLSON_dementia",
                  "CHARLSON_hemiplegia",
                  "CHARLSON_connective_tissue_disease",
                  "CHARLSON_peptic_ulcer",
                  "death_upto_1year"
                  ]#32

        text = ["Specialty",
                "Surgery_location",
                "Preoperative_diagnosis",
                "Operation_performed",
                "procedures_COD",
                "post_surgical_complication_description",
                "complication_COD",
                "Additional_Info",
                "comorbidities_info"
                ]#9

        dates = [#   "data pedido pela anestesia",
                # "data admissão UCI",
                # "data cirurgia",
                # "data óbito",
                "Anesthesia_request_Date",
                "UCI_Admission_Date",
                "Surgery_Date",
                "death_date"
                ]#4

        ignored_columns = [  #"Intervenções_ICD10",
                            #"ACS_procedimento",
                            #"nº IPO",
                            #"classificação ACS complicações específicas",
                           "Interventions_ICD10",
                           "ACS_procedure",
                           "IPONumber",
                           "ACS_classification_specific_complications"
                           ]#4

        ignored_feature_in_ranking = [
           # "óbito até 1 ano ",
             "death_upto_1year"
        ]

       #class_label = ["complicação pós-cirúrgica", "classificação clavien-dindo"]

       #numerical_continuous = ["dias na UCI",
       #                 "total pontos NAS",
       #                 "pontos NAS por dia",
       #                 "% morbilidade P-Possum",
       #                 "% mortalidade P-Possum",
       #                 "ACS peso",
       #                 "complicações sérias (%)",
       #                 "qualquer complicação (%)",
       #                 "pneumonia (%)",
       #                "complicações cardíacas (%)",
       #                 "infeção cirúrgica (%)",
       #                "ITU (%)",
       #                "tromboembolismo venoso (%)",
       #                "falência renal (%)",
       #                "ileus (%)",
       #                "fuga anastomótica (%)",
       #                "readmissão (%)",
       #                "reoperação (%)",
       #                "morte (%)",
       #                "Discharge to Nursing or Rehab Facility (%)",
       #                "risco médio - complicações sérias (%)",
       #                "risco médio - qualquer complicação (%)",
       #                "risco médio - pneumonia (%)",
       #                "risco médio - complicações cardíacas (%)",
       #                "risco médio - infeção cirúrgica (%)",
       #                "risco médio - ITU (%)",
       #                "risco médio - tromboembolismo venoso (%)",
       #                "risco médio - falência renal (%)",
       #                "risco médio - ileus (%)",
       #                "risco médio - fuga anastomótica (%)",
       #                "risco médio - readmissão (%)",
       #                "risco médio - reoperação (%)",
       #                 "risco médio - morte (%)",
       #                 "risco médio - Discharge to Nursing or Rehab Facility (%)",
       #                 "ACS - previsão dias internamento"
       #                 ] #35

       #categorical = ["tipo pedido anestesia",
       #        "proveniência",
       #        "motivo admissão UCI",
       #        "tipo cirurgia",
       #        "especialidade_COD",
       #        "destino após UCI",
       #        "ASA",
       #        "PP idade",
       #        "PP cardíaco",
       #        "PP respiratório",
       #        "PP ECG",
       #        "PP pressão arterial sistólica",
       #        "PP pulsação arterial",
       #        "PP hemoglobina",
       #        "PP leucócitos",
       #        "PP ureia",
       #        "PP sódio",
       #        "PP potássio",
       #        "PP escala glasglow",
       #        "PP tipo operação",
       #        "PP nº procedimentos",
       #        "PP perda sangue",
       #        "PP contaminação peritoneal",
       #        "PP estado da malignidade",
       #        "PP CEPOD-classificação operação",
       #        "ACS idade",
       #        "ACS estado funcional",
       #        "ACS ASA",
       #        "ACS sépsis sistémica",
       #        "ACS diabetes",
       #        "ACS dispneia",
       #        "ARISCAT Idade",
       #        "ARISCAT SpO2 ",
       #        "ARISCAT incisão cirúrgica",
       #        "ARISCAT duração cirurgia",
       #        "SCORE ARISCAT",
       #        "CHARLSON Idade",
       #        "CHARLSON Diabetes mellitus",
       #        "CHARLSON Doença fígado",
       #        "CHARLSON Malignidade",
       #        "complicação principal_COD",
       #        "classificação ACS complicações gerais",
       #        "destino após IPO",
       #        "óbito_tempo decorrido após data cirurgia (até 1 ano)"
       #       ]#44

       # numerical_discrete = ["idade",
       #               "dias no  IPOP",
       #               "Score fisiológico P-Possum",
       #               "Score gravidade cirúrgica P-Possum",
       #               "ACS altura",
       #               "ARISCAT PONTUAÇÃO TOTAL",
       #               "PONTOS - Charlson Comorbidity Index",
       #               "% Sobrevida estimada em 10 anos"
       #               ]#8

       #  binary = ["1ª Cirurgia IPO",
       #   "QT pré-operatória",
       #   " reinternamento na UCI",
       #   "género",
       #   "ACS género",
       #   "ACS emergência",
       #   "ACS esteróides",
       #   "ACS ascite",
       #   "ACS dependente ventilador",
       #   "ACS cancro disseminado",
       #   "ACS hipertensão",
       #   "ACS ICC",
       #   "ACS fumador",
       #   "ACS DPOC",
       #  "ACS diálise",
       #   "ACS insuficiência renal aguda",
       #  "ARISCAT infeção respiratória último mês",
       #   "ARISCAT anemia pré-operativa",
       #   "ARISCAT procedimento emergente",
       #   "CHARLSON SIDA",
       #   "CHARLSON Doença Renal Crónica Moderada a Severa",
       #   "CHARLSON Insuficiência Cardíaca",
       #   "CHARLSON Enfarte Miocárdio",
       #   "CHARLSON DPOC",
       #   "CHARLSON Doença Vascular periférica",
       #   "CHARLSON AVC ou Ataque Isquémico Transitório",
       #   "CHARLSON Demência",
       #   "CHARLSON Hemiplegia",
       #   "CHARLSON Doença do Tecido Conjuntivo",
       #   "CHARLSON Úlcera Péptica",
       #   "óbito até 1 ano "
       #   ]#32

       #  text = ["especialidade",
       # "LOCALIZAÇÃO ",
       # "diagnóstico pré-operatório",
       # "Operação efetuada",
       # "procedimentos_COD",
       # "descrição complicação pós-cirúrgica",
       # "complicação_COD",
       # "Informação adicional",
       # "Comorbilidades pré-operatórias"
       # ]#9

       #dates = ["data pedido pela anestesia",
       # "data admissão UCI",
       #  "data cirurgia",
       #  "data óbito"
                 #]#4

        #ignored_columns = ["Intervenções_ICD10",
        #           "ACS_procedimento",
        #           "nº IPO",
        #          "classificação ACS complicações específicas"
       #           ]#4

       # ignored_feature_in_ranking = [
       # "óbito até 1 ano "
       # ]

        prefix_for_generated_columns = ["ACS_",
                                        "ICD_",
                                        "ACS_CE_"
                                        ]#3
