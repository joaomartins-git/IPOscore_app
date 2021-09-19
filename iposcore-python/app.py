import os
from flask import Flask, render_template, send_from_directory, jsonify, make_response
from cache import Cache
#from flask_mysqldb import MySQL
import json

import pandas as pd
from sqlalchemy import create_engine
import pymysql

app = Flask(__name__,
            template_folder="templates",
            static_url_path="",
            static_folder="public"
            )

#app.config['MYSQL_USER'] = 'root'
#app.config['MYSQL_PASSWORD'] = 'rootroot'
#app.config['MYSQL_HOST'] = 'localhost'
#app.config['MYSQL_DB'] = 'TeseApp'
#app.config['MYSQL_PORT'] = 3306
#app.config['MYSQL_CURSORCLASS'] = 'DictCursor'

#mysql = MySQL(app)

sql_engine = create_engine('mysql+pymysql://root:rootroot@localhost:3306/TeseApp')
# query = "select p.*, sd.*, aris.*, acs.*, uci.*, ch.*, ic.*, pp.*, post.*, hd.*, pc.* from Patients p LEFT JOIN Surgery_Data sd on p.IPONumber = sd.patientNumber LEFT JOIN ARISCAT aris on sd.id = aris.surgeryData_id LEFT JOIN ACS_Risk_Calculator acs on sd.id = acs.surgeryData_id LEFT JOIN Application_for_UCI_Admission uci on sd.id = uci.surgeryData_id LEFT JOIN CHARLSON ch on sd.id = ch.surgeryData_id LEFT JOIN Internment_caracteristics ic on sd.id = ic.surgeryData_id LEFT JOIN P_POSSUM pp on sd.id = pp.surgeryData_id LEFT JOIN Post_Surgical_complications post on sd.id = post.surgeryData_id LEFT JOIN Hospital_Discharge hd on sd.id = hd.surgeryData_id LEFT JOIN Preoperative_comorbidities pc on sd.id = pc.surgeryData_id"
query_2 = "select pd.Age, pd.Gender, uci.Anesthesia_request_type, ic.Provenace, ic.Admission_Motive, ic.Surgery_Type, ic.Specialty_COD, ic.UCI_days, ic.IPOP_days, ic.destiny_after_Uci, ic.NAS_totalpoints, ic.NAS_pointsperday, ic.FirstSurgery_in_IPO, ic.Preoperative_QT, ic.Readmission_UCI, pp.PP_age, pp.PP_cardiac, pp.PP_respiratory, pp.PP_ECG, pp.PP_systolic_blood_pressure ,pp.PP_artirial_pulse, pp.PP_hemoglobin, pp.PP_leukocytes, pp.PP_urea, pp.PP_potassium, pp.PP_sodium, pp.PP_Glasgow_scale, pp.PP_operation_type, pp.PP_number_of_procedures, pp.PP_blood_loss, pp.PP_peritoneal_contamination, pp.PP_state_of_malignancy, pp.PP_CEPOD_grading_operation, pp.P_Possum_Physiological_Score, pp.P_Possum_Surgical_Severity_Score, pp.P_Possum_Mortality_Percentage, pp.P_Possum_Morbidity_Percentage, acs.ACS_age, acs.ACS_gender, acs.ACS_functional_state, acs.ACS_emergency, acs.ACS_ASA, acs.ACS_steroids, acs.ACS_ascites, acs.ACS_systemic_sepsis, acs.ACS_ventilator_dependent, acs.ACS_disseminated_cancer, acs.ACS_diabetes, acs.ACS_hypertension, acs.ACS_ICC, acs.ACS_dyspnea, acs.ACS_smoker, acs.ACS_DPOC, acs.ACS_dialysis, acs.ACS_acute_renal_failure, acs.ACS_height, acs.ACS_weight, acs.Serious_complications_percentage, acs.Average_risk_serious_complications, acs.Any_complications_percentage, acs.Average_risk_any_complications, acs.Pneumonia_percentage, acs.Average_risk_pneumonia, acs.Cardiac_complications_percentage, acs.Average_risk_cardiac_complications, acs.Surgical_infection_percentage, acs.Average_risk_surgical_infection, acs.ITU_percentage, acs.Average_risk_ITU, acs.Venous_thromboembolism_percentage, acs.Average_risk_venous_thromboembolism, acs.Kidney_failure_percentage, acs.Average_risk_kidney_failure, acs.Ileus_percentage, acs.Average_risk_ileus, acs.Anastomotic_leakage_percentage, acs.Average_risk_Anastomotic_leakage, acs.Readmission_percentage, acs.Average_risk_readmission, acs.Reoperation_percentage, acs.Average_risk_reoperation, acs.Death_percentage, acs.Average_risk_death, acs.Discharge_To_Nursing_or_Rehab_Facility_percentage, acs.Average_risk_discharge_nursing_rehab, acs.ACS_days_prevision, ar.ARISCAT_age, ar.ARISCAT_SpO2, ar.ARISCAT_respiratory_infection_last_month, ar.ARISCAT_preoperative_anemia, ar.ARISCAT_surgical_incision, ar.ARISCAT_emerging_Procedure, ar.ARISCAT_Total_points, ar.ARISCAT_surgery_duration, ar.ARISCAT_Score, ch.CHARLSON_age, ch.CHARLSON_diabetes_mellitus, ch.CHARLSON_liver_disease, ch.CHARLSON_malignancy_solid_tumor, ch.CHARLSON_AIDS, ch.CHARLSON_chronic_kidney_disease, ch.CHARLSON_heart_failure, ch.CHARLSON_myocardial_infarction, ch.CHARLSON_DPOC, ch.CHARLSON_peripheral_vascular_disease, ch.CHARLSON_AVC, ch.CHARLSON_dementia, ch.CHARLSON_hemiplegia, ch.CHARLSON_connective_tissue_disease, ch.CHARLSON_peptic_ulcer, ch.POINTS_Charlson_Comorbidity_Index, post.post_surgical_complication, post.post_surgical_complication_description, post.complication_principal_COD, post.complication_COD, post.ACS_classification_general_complications, post.Clavien_Dindo_classification, hd.destiny_after_IPO_Discharge, hd.death_upto_1year, hd.death_time_elapsed_after_surgerydate from Patients pd LEFT JOIN Surgery_Data sd on pd.IPONumber = sd.patientNumber LEFT JOIN ARISCAT ar on sd.id = ar.surgeryData_id LEFT JOIN ACS_Risk_Calculator acs on sd.id = acs.surgeryData_id LEFT JOIN Application_for_UCI_Admission uci on sd.id = uci.surgeryData_id LEFT JOIN CHARLSON ch on sd.id = ch.surgeryData_id LEFT JOIN Internment_caracteristics ic on sd.id = ic.surgeryData_id LEFT JOIN P_POSSUM pp on sd.id = pp.surgeryData_id LEFT JOIN Post_Surgical_complications post on sd.id = post.surgeryData_id LEFT JOIN Hospital_Discharge hd on sd.id = hd.surgeryData_id LEFT JOIN Preoperative_comorbidities pc on sd.id = pc.surgeryData_id"



@app.route("/dbtest", methods=['GET'])
def index():
    df = pd.read_sql_query(query_2, sql_engine)
    # cur = mysql.connection.cursor()
    # results = cur.execute("SELECT * FROM Patients ")
     #select p.*, sd.*, aris.*, acs.*, uci.*, ch.*, ic.*, pp.*, post.*, hd.*, pc.* from Patients p
     #LEFT JOIN Surgery_Data sd on p.IPONumber = sd.patientNumber
     #LEFT JOIN ARISCAT aris on sd.id = aris.surgeryData_id
     #LEFT JOIN ACS_Risk_Calculator acs on sd.id = acs.surgeryData_id
     #LEFT JOIN Application_for_UCI_Admission uci on sd.id = uci.surgeryData_id
     #LEFT JOIN CHARLSON ch on sd.id = ch.surgeryData_id
     #LEFT JOIN Internment_caracteristics ic on sd.id = ic.surgeryData_id
     #LEFT JOIN P_POSSUM pp on sd.id = pp.surgeryData_id
     #LEFT JOIN Post_Surgical_complications post on sd.id = post.surgeryData_id
     #LEFT JOIN Hospital_Discharge hd on sd.id = hd.surgeryData_id
     #LEFT JOIN Preoperative_comorbidities pc on sd.id = pc.surgeryData_id;

    if not df.empty:
        print(df)
        return df.__dict__

    #transformar o df em JSON



@app.route("/favicon.ico")
def favicon():
    return send_from_directory(os.path.join(app.root_path, "public"), "favicon.ico",
                               mimetype="image/vnd.microsoft.icon")


@app.route("/feature_ranking")
def feature_ranking():
    return render_template("feature_ranking.html", data=Cache.feature_ranking)


@app.route("/exploration")
def exploration():
    return render_template("exploration.html", data=Cache.exploration)


@app.route("/")
def home():
    return render_template("home.html")


@app.route('/data_exploration')
def data_exploration():
    d = Cache.exploration
    return jsonify(d)


@app.route('/data_feature_ranking')
def data_feature_ranking():
    d = Cache.feature_ranking
    return jsonify(d)


@app.route('/data_feature_ranking')
def score_calculation():
    d = Cache.feature_ranking
    return jsonify(d)


if __name__ == "__main__":
    Cache.fill_cache()
    # print(Cache.exploration)
    app.run(port=5010, debug=True)
