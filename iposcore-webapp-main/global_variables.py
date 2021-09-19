outputs=[
'complicação pós-cirúrgica',                        # CHECK     classificação
'classificação clavien-dindo',                      # CHECK2    regressão (classificação)
'dias na UCI',                                      # CHECK     regressão
'óbito até 1 ano ',                                 # CHECK     classificação
'tempo decorrido após cirurgia (óbito)_até 1 ano',  # CHECK     regressão (classificação)
'total pontos NAS',                                 # CHECK     regressão
'dias no  IPOP'                                     # CHECK     regressão
]#7

post_surgical=[
'descrição complicação pós-cirúrgica',
'complicação_COD',
'classificação ACS complicações gerais',
'classificação ACS complicações específicas',
'destino após IPO',
'data óbito',
'Informação adicional',
' reinternamento na UCI',
'pontos NAS por dia',
'motivo admissão UCI',
'destino após UCI',
'data admissão UCI',
'proveniência',
'tipo pedido anestesia',
'Operação efetuada',
'Intervenções_ICD10',
'PP perda sangue'
]#16

numericas=[
'idade',
'ACS altura',
'ACS peso'
]#3

nominais = [
'especialidade',
'Patologia',
'diagnóstico pré-operatório',
#'procedimentos_COD',                               # missing values
'ACS_procedimento',
'Comorbilidades pré-operatórias'
] #5
# especialidade tem substituto (COD), Patologia tem missing values...

categoricas = [
# 'tipo cirurgia',                                      # low variance (0.1)
'especialidade_COD'
]#2

binarias = [
'género',
# '1ª Cirurgia IPO',                                    # high correlation
'QT pré-operatória',
# 'ACS género',                                         # repetida
# 'ACS emergência',                                     # repetida
'ACS esteróides',
# 'ACS ascite',                                         # low variance (0.1)
# 'ACS dependente ventilador',                          # Low Variance
'ACS cancro disseminado',
'ACS hipertensão',
'ACS ICC',
'ACS fumador',
'ACS DPOC',
# 'ACS diálise',                                        # Low variance
'ACS insuficiência renal aguda',
'ARISCAT infeção respiratória último mês',
'ARISCAT anemia pré-operativa',
'ARISCAT procedimento emergente'
# 'CHARLSON SIDA',                                      # missing values...
# 'CHARLSON Doença Renal Crónica Moderada a Severa',
# 'CHARLSON Insuficiência Cardíaca',
# 'CHARLSON Enfarte Miocárdio',
# 'CHARLSON DPOC',
# 'CHARLSON Doença Vascular periférica',
# 'CHARLSON AVC ou Ataque Isquémico Transitório',
# 'CHARLSON Demência',
# 'CHARLSON Hemiplegia',
# 'CHARLSON Doença do Tecido Conjuntivo',
# 'CHARLSON Úlcera Péptica'
]#16

datas = [
'data pedido anestesia',
'data cirurgia'             
]#2

ordinais = [
'ASA',
# 'PP idade',                                           # repetido
'PP respiratório',
'PP ECG',
'PP pulsação arterial',
'PP pressão arterial sistólica',
'PP cardíaco',
'PP hemoglobina',
'PP leucócitos',
'PP ureia',
'PP sódio',
'PP potássio',
# 'PP escala glasglow',                                 # low variance
# 'PP tipo operação',                                   # low variance
'PP nº procedimentos',
'PP contaminação peritoneal',
'PP estado da malignidade',
# 'PP CEPOD-classificação operação',                    # high correlation
# 'ACS idade',                                          # repetido
'ACS estado funcional',
# 'ACS ASA',                                            # repetido
'ACS sépsis sistémica',
'ACS diabetes',
'ACS dispneia',
'ARISCAT Idade',
# 'ARISCAT SpO2 ',                                     # low variance (0.1)
# 'ARISCAT incisão cirúrgica',                         # high correlation
'ARISCAT duração cirurgia'
# 'CHARLSON Idade',                                    # missing values
# 'CHARLSON Diabetes mellitus',                        # missing values
# 'CHARLSON Doença fígado',                            # missing values
# 'CHARLSON Malignidade'                               # missing values
]#27

res_calculadoras = [
'Score fisiológico P-Possum',
'Score gravidade cirúrgica P-Possum',
'% morbilidade P-Possum',
'% mortalidade P-Possum',
'complicações sérias (%)',
'risco médio',
'qualquer complicação (%)',
'risco médio',
'pneumonia (%)',
'risco médio',
'complicações cardíacas (%)',
'risco médio',
'infeção cirúrgica (%)',
'risco médio',
'ITU (%)',
'risco médio',
'tromboembolismo venoso (%)',
'risco médio',
'falência renal (%)',
'risco médio',
'ileus (%)',
'risco médio',
'fuga anastomótica (%)',
'risco médio',
'readmissão (%)',
'risco médio',
'reoperação (%)',
'risco médio',
'morte (%)',
'risco médio',
'Discharge to Nursing or Rehab Facility (%)',
'risco médio',
'ACS - previsão dias internamento',
'ARISCAT PONTUAÇÃO TOTAL',
'SCORE ARISCAT',
'PONTOS - Charlson Comorbidity Index',
'% Sobrevida estimada em 10 anos'
]#37
