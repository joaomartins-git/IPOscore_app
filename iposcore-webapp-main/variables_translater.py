list1 = ["dias na UCI","complicação pós-cirúrgica","classificação clavien-dindo","óbito até 1 ano ","dias no  IPOP","óbito_tempo decorrido após data cirurgia (até 1 ano)","idade","Score fisiológico P-Possum","Score gravidade cirúrgica P-Possum","% morbilidade P-Possum","% mortalidade P-Possum","ACS altura","ACS peso","complicações sérias (%)","risco médio","qualquer complicação (%)","risco médio.1","pneumonia (%)","risco médio.2","complicações cardíacas (%)","risco médio.3","infeção cirúrgica (%)","risco médio.4","ITU (%)","risco médio.5","tromboembolismo venoso (%)","risco médio.6","falência renal (%)","risco médio.7","readmissão (%)","risco médio.10","reoperação (%)","risco médio.11","morte (%)","risco médio.12","Discharge to Nursing or Rehab Facility (%)","risco médio.13","ACS - previsão dias internamento","ARISCAT PONTUAÇÃO TOTAL","SCORE ARISCAT","especialidade","LOCALIZAÇÃO ","diagnóstico pré-operatório","Comorbilidades pré-operatórias","ACS_procedimento","tipo cirurgia","especialidade_COD","ASA","PP idade","PP cardíaco","PP respiratório","PP ECG","PP pressão arterial sistólica","PP pulsação arterial","PP hemoglobina","PP leucócitos","PP ureia","PP sódio","PP potássio","PP escala glasglow","PP tipo operação","PP nº procedimentos","PP perda sangue","PP contaminação peritoneal","PP estado da malignidade","PP CEPOD-classificação operação","ACS idade","ACS estado funcional","ACS ASA","ACS sépsis sistémica","ACS diabetes","ACS dispneia","ARISCAT Idade","ARISCAT SpO2 ","ARISCAT incisão cirúrgica","ARISCAT duração cirurgia","género","1ª Cirurgia IPO","QT pré-operatória","ACS género","ACS emergência","ACS esteróides","ACS ascite","ACS dependente ventilador","ACS cancro disseminado","ACS hipertensão","ACS ICC","ACS fumador","ACS DPOC","ACS diálise","ACS insuficiência renal aguda","ARISCAT infeção respiratória último mês","ARISCAT anemia pré-operativa","ARISCAT procedimento emergente","data cirurgia"]

list2 = ["Days in ICU","Postoperative Complication","Clavien-Dindo Classification","1 Year Death","Days at IPOP","Death - Time After Surgery (1 Year Limit)","Age","Score Physiological P-Possum","Score Surgical Gravity P-Possum","% Morbidity P-Possum","% Mortality P-Possum","ACS Height","ACS Weight","Serious Complications (%)","Serious Complications Average Risk","Any Complication (%)","Any Complication Average Risk","Pneumonia (%)","Pneumonia Average Risk","Heart Complications (%)","Heart Complications Average Risk","Surgical Infection (%)","Surgical Infection Average Risk","UTI (%)","UTI Average Risk","Venous Thromboembolism (%)","Venous Thromboembolism Average Risk","Renal Failure (%)","Renal Failure Average Risk","Readmission (%)","Readmission Average Risk","Reoperation (%)","Reoperation Average Risk","Death (%)","Death Average Risk","Discharge to Nursing or Rehab Facility (%)","Discharge to Nursing or Rehab Facility Average Risk","ACS - Internment Days Prediction","ARISCAT TOTAL SCORE","SCORE ARISCAT","Specialty","LOCATION","Preoperative Diagnostic","Preoperative Comorbilities","ACS_Procedure","Surgery Type","Specialty_COD","ASA","PP Age","PP Cardiac","PP Respiratory","PP EKG","PP Systolic Blood Pressure","PP Pulse","PP Hemoglobin","PP Leukocytes","PP Urea","PP Sodium","PP Potassium","PP Glasglow Scale","PP Surgery Type","PP N. of Procedure","PP Blood Loss","PP Peritoneal Contamination","PP Malignancy State","PP CEPOD-Surgery Classification","ACS Age","ACS Functional State","ACS ASA","ACS Systemic Sepsis","ACS Diabetes","ACS Dyspnoea","ARISCAT Age","ARISCAT SpO2 ","ARISCAT Surgical Incision","ARISCAT Surgery Duration","Gender","1st Surgery at IPO","Preoperative Chemo","ACS Gender","ACS Emergency","ACS Steroids","ACS Ascites","ACS Ventilator Dependent","ACS Disseminated cancer","ACS Hypertension","ACS CHF","ACS Smoker","ACS COPD","ACS Dialysis","ACS Acute Renal Failure","ARISCAT Last Month Respiratory Infection","ARISCAT Preoperative Anemia","ARISCAT Emergent Procedure","Date of Surgery"]

dictionary = dict(zip(list1, list2))

def tradutor(lista):
    for i in range(0,len(lista)):
        try:
            lista[i]=dictionary[lista[i]]
        except:
            parts = lista[i].split("_")
            lista[i]=dictionary[parts[0]]+"_"+parts[1]

    return lista

# lista = ['pneumonia (%)', 'complicações sérias (%)', 'qualquer complicação (%)', 'ACS - previsão dias internamento', 'readmissão (%)', 'Discharge to Nursing or Rehab Facility (%)', '% morbilidade P-Possum', 'reoperação (%)', 'morte (%)', '% mortalidade P-Possum', 'tromboembolismo venoso (%)', 'Score fisiológico P-Possum', 'Score gravidade cirúrgica P-Possum', 'complicações cardíacas (%)', 'falência renal (%)', 'ITU (%)']
# print(tradutor(lista))




outputs=[
            "Days in ICU",
            "Postoperative Complication",
            "Clavien-Dindo Classification",
            "1 Year Death",
            "Days at IPOP",
            "Death - Time After Surgery (1 Year Limit)"
        ] # 6

numericas=[
            "Age",
            # "total pontos NAS",       # POS
            # "pontos NAS por dia",     # POS
            "Score Physiological P-Possum",
            "Score Surgical Gravity P-Possum",
            "% Morbility P-Possum",
            "% Mortality P-Possum",
            "ACS Height",
            "ACS Weight",
            "Serious Complications (%)",
            "Serious Complications Average Risk",
            "Any Complication (%)",
            "Any Complication Average Risk",
            "Pneumonia (%)",
            "Pneumonia Average Risk",
            "Heart Complications (%)",
            "Heart Complications Average Risk",
            "Surgical Infection (%)",
            "Surgical Infection Average Risk",
            "UTI (%)",
            "UTI Average Risk",
            "Venous Thromboembolism (%)",
            "Venous Thromboembolism Average Risk",
            "Renal Failure (%)",
            "Renal Failure Average Risk",
            # "ileus (%)",              # MISSING VALUES
            # "risco médio.8",          # MISSING VALUES
            # "fuga anastomótica (%)",  # MISSING VALUES
            # "risco médio.9",          # MISSING VALUES
            "Readmission (%)",
            "Readmission Average Risk",
            "Reoperation (%)",
            "Reoperation Average Risk",
            "Death (%)",
            "Death Average Risk",
            "Discharge to Nursing or Rehab Facility (%)",
            "Discharge to Nursing or Rehab Facility Average Risk",
            "ACS - Internment Days Prediction",
            "ARISCAT TOTAL SCORE",
            "SCORE ARISCAT"
            # "PONTOS - Charlson Comorbidity Index", # MISSING VALUES
            # "% Sobrevida estimada em 10 anos"      # MISSING VALUES
            ] # 42 ou 34 efetivas

nominais = [
            "Specialty",
            "LOCATION",                         #muito fácil de usar estratégia de bags
            "Preoperative Diagnostic",
            #"Operação efetuada",                                             # POS
            #"procedimentos_COD",                   #muitos missing           # POS
            #"descrição complicação pós-cirúrgica",                           # POS
            #"complicação_COD",                     #muitos missing           # POS
            # "Informação adicional",               #muitos missing           # POS
            "Preoperative Comorbilities"
        ] # 9 ou 4 efetivas

codigos = [
            #"Intervenções_ICD10",                  # POS
            "ACS_Procedure"
        ] # 2 ou 1 efetiva

categoricas = [
                #"tipo pedido anestesia",           # POS
                #"proveniência",                    # POS
                #"motivo admissão UCI",             # POS
                "Surgery Type",
                "Specialty_COD",
                #"destino após UCI",                # POS
                "ASA",
                "PP Age",
                "PP Cardiac",
                "PP Respiratory",
                "PP EKG",
                "PP Systolic Blood Pressure",
                "PP Pulse",
                "PP Hemoglobin",
                "PP Leukocytes",
                "PP Urea",
                "PP Sodium",
                "PP Potassium",
                "PP Glasglow Scale",
                "PP Surgery Type",
                "PP N. of Procedure",
                "PP Blood Loss",
                "PP Peritoneal Contamination",
                "PP Malignancy State",
                "PP CEPOD-Surgery Classification",
                "ACS Age",
                "ACS Functional State",
                "ACS ASA",
                "ACS Systemic Sepsis",
                "ACS Diabetes",
                "ACS Dyspnoea",
                "ARISCAT Age",
                "ARISCAT SpO2 ",
                "ARISCAT Surgical Incision",
                "ARISCAT Surgery Duration"
                # "CHARLSON Idade",                                     # MISSING VALUES
                # "CHARLSON Diabetes mellitus",                         # MISSING VALUES
                # "CHARLSON Doença fígado",                             # MISSING VALUES
                # "CHARLSON Malignidade",                               # MISSING VALUES
                #"complicação principal_COD",                           # POS
                #"classificação ACS complicações específicas",          # POS
                #"destino após IPO"                                     # POS
                ] # 42 ou 31 efetivas

binarias = [
            "Gender",
            "1st Surgery at IPO",
            "Preoperative Chemo",
            #" reinternamento na UCI",      # POS
            "ACS Gender",
            "ACS Emergency",
            "ACS Steroids",
            "ACS Ascites",
            "ACS Ventilator Dependent",
            "ACS Disseminated cancer",
            "ACS Hypertension",
            "ACS CHF",
            "ACS Smoker",
            "ACS COPD",
            "ACS Dialysis",
            "ACS Acute Renal Failure",
            "ARISCAT Last Month Respiratory Infection",
            "ARISCAT Preoperative Anemia",
            "ARISCAT Emergent Procedure"
            # "CHARLSON SIDA",
            # "CHARLSON Doença Renal Crónica Moderada a Severa",    # MISSING VALUES
            # "CHARLSON Insuficiência Cardíaca",                    # MISSING VALUES
            # "CHARLSON Enfarte Miocárdio",                         # MISSING VALUES
            # "CHARLSON DPOC",                                      # MISSING VALUES
            # "CHARLSON Doença Vascular periférica",                # MISSING VALUES
            # "CHARLSON AVC ou Ataque Isquémico Transitório",       # MISSING VALUES
            # "CHARLSON Demência",                                  # MISSING VALUES
            # "CHARLSON Hemiplegia",                                # MISSING VALUES
            # "CHARLSON Doença do Tecido Conjuntivo",               # MISSING VALUES
            # "CHARLSON Úlcera Péptica",                            # MISSING VALUES
            #"classificação ACS complicações gerais"                # POS
        ] # 31 ou 18 efetivas

datas = [
            #"data pedido pela anestesia",      # POS
            #"data admissão UCI",               # POS
            "Date of Surgery"
            #"data óbito"  # muitos missing     # POS
        ] # 4 ou 1 efetiva