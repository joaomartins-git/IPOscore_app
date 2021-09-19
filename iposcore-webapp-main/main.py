'''
@info IPOscore tool
@version 1.0
'''
import base64
import pickle

import dash
import dash_bootstrap_components as dbc
import dash_core_components as dcc
import dash_html_components as html
import pandas as pd
import plotly.express as px
from dash.dependencies import Input, Output, State
from dash.exceptions import PreventUpdate

import global_variables
import load_and_run
import translater

flag = 0

df = load_and_run.load_data('complicação pós-cirúrgica', ['idade'])
fig = px.violin(df, y='idade', x='complicação pós-cirúrgica', box=True, points="all", hover_data=df.columns)

app = dash.Dash(__name__, assets_folder='assets', external_stylesheets=[dbc.themes.BOOTSTRAP])
app.title = "IPOscore"
server = app.server

ipoScoreLogo = 'IPOScoreLogo.png'
encoded_image = base64.b64encode(open(ipoScoreLogo, 'rb').read()).decode('ascii')


def page(flag):
    return html.Div([
        dbc.Row(
            [
                dbc.Col(html.Img(src='data:image/png;base64,{}'.format(encoded_image)), width=6),
                dbc.Col(
                    html.Div([
                        dbc.Button('EN', id='en_lang', color='primary',
                                   style={'float': 'right', 'margin-top': 5, 'margin-left': 'auto', 'margin-right': 5}),
                        dbc.Button('PT', id='pt_lang', color='primary',
                                   style={'float': 'right', 'margin-top': 5, 'margin-left': 'auto', 'margin-right': 5})
                    ])
                )
            ], justify='between'),
        dcc.Tabs([
            dcc.Tab(label=translater.dic["home"][flag],
                    children=[html.Div([
                        html.Div(style={"height": 50}),
                        dbc.Row([
                            dbc.Col(html.Div([
                                html.H6(translater.dic["ARISCAT_respiratory_infection"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["yes"][flag], 'value': '2'},
                                        {'label': translater.dic["no"][flag], 'value': '1'},
                                    ],
                                    value='1',
                                    id="home-1"
                                ),

                                html.H6(translater.dic["ARISCAT_surgery_duration"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["<2_hours"][flag], 'value': '1'},
                                        {'label': translater.dic["between_2_3_hours"][flag], 'value': '2'},
                                        {'label': translater.dic[">3_hours"][flag], 'value': '3'},
                                    ],
                                    value='1',
                                    id="home-2"
                                ),

                                html.H6(translater.dic["ACS_systemic_sepsis"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["none"][flag], 'value': '1'},
                                        {'label': translater.dic["sirs"][flag], 'value': '2'},
                                        {'label': translater.dic["sepsis"][flag], 'value': '3'},
                                        {'label': translater.dic["septic_shock"][flag], 'value': '4'}
                                    ],
                                    value='1',
                                    id="home-3"
                                ),

                                html.H6(translater.dic["PP_peritoneal_contamination"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["no_contamination"][flag], 'value': '1'},
                                        {'label': translater.dic["minimal_contamination"][flag], 'value': '2'},
                                        {'label': translater.dic["local_pus"][flag], 'value': '3'},
                                        {'label': translater.dic["free_contents"][flag], 'value': '4'}
                                    ],
                                    value='1',
                                    id="home-4"
                                ),

                                html.H6(translater.dic["PP_hemoglobin"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': '13-16 g/dL', 'value': '1'},
                                        {'label': '11.5-12.9 ou 16.1-17 g/dL', 'value': '2'},
                                        {'label': '10-11.4 ou 17.1-18 g/dL', 'value': '3'},
                                        {'label': '<10 ou >18 g/dL', 'value': '4'}
                                    ],
                                    value='1',
                                    id="home-5"
                                ),

                                html.H6('PP ECG'),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["normal_ECG"][flag], 'value': '1'},
                                        {'label': translater.dic["ECG_AF"][flag], 'value': '2'},
                                        {'label': translater.dic["abnormal_rhythm"][flag], 'value': '3'}
                                    ],
                                    value='1',
                                    id="home-6"
                                ),

                                html.H6('ASA'),
                                dcc.Dropdown(
                                    options=[
                                        {'label': 'ASA 1', 'value': '1'},
                                        {'label': 'ASA 2', 'value': '2'},
                                        {'label': 'ASA 3', 'value': '3'},
                                        {'label': 'ASA 4', 'value': '4'},
                                        {'label': 'ASA 5', 'value': '5'}
                                    ],
                                    value='1',
                                    id="home-7"
                                ),

                                html.H6(translater.dic["PP_status_of_malignancy"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["no_malignancy"][flag], 'value': '1'},
                                        {'label': translater.dic["primary_malignancy"][flag], 'value': '2'},
                                        {'label': translater.dic["nodal_metastases"][flag], 'value': '3'},
                                        {'label': translater.dic["distant_metastasis"][flag], 'value': '4'}
                                    ],
                                    value='1',
                                    id="home-8"
                                ),

                                html.H6(translater.dic["ACS_dyspnea"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["no"][flag], 'value': '1'},
                                        {'label': translater.dic["moderate_effort"][flag], 'value': '2'},
                                        {'label': translater.dic["at_rest"][flag], 'value': '3'},
                                    ],
                                    value='1',
                                    id="home-9",
                                ),

                                html.H6(translater.dic["PP_urea"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': '<7.6', 'value': '1'},
                                        {'label': '7.6-10', 'value': '2'},
                                        {'label': '10.1-15', 'value': '3'},
                                        {'label': '>15', 'value': '4'}
                                    ],
                                    value='1',
                                    id="home-10"
                                ),
                            ]), width=4),
                            dbc.Col(html.Div([
                                html.H6(translater.dic["PP_sodium"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': '>135 mmol/L', 'value': '1'},
                                        {'label': '131-135 mmol/L', 'value': '2'},
                                        {'label': '126-130 mmol/L', 'value': '3'},
                                        {'label': '<126 mmol/L', 'value': '4'}
                                    ],
                                    value='4',
                                    id="home-11"
                                ),

                                html.H6(translater.dic["ACS_acute_renal_failure"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["no"][flag], 'value': '1'},
                                        {'label': translater.dic["yes"][flag], 'value': '2'}
                                    ],
                                    value='1',
                                    id="home-12",

                                ),

                                html.H6(translater.dic["ACS_weight"][flag]),
                                dcc.Input(id='home-13', type='text'),

                                html.H6(translater.dic["ARISCAT_emergent_procedure"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["no"][flag], 'value': '1'},
                                        {'label': translater.dic["yes"][flag], 'value': '2'}
                                    ],
                                    value='1',
                                    id="home-14",

                                ),

                                html.H6(translater.dic["ACS_functional_status"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["independent"][flag], 'value': '1'},
                                        {'label': translater.dic["partially_dependent"][flag], 'value': '2'},
                                        {'label': translater.dic["fully_dependent"][flag], 'value': '3'}
                                    ],
                                    value='1',
                                    id="home-15",
                                ),

                                html.H6(translater.dic["PP_leukocytes"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': '4-10', 'value': '1'},
                                        {'label': '10.1-20 ou 3.1-4', 'value': '2'},
                                        {'label': '>20 ou <3', 'value': '3'}
                                    ],
                                    value='1',
                                    id="home-16"
                                ),

                                html.H6(translater.dic["PP_respiratory"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["no_dyspnea"][flag], 'value': '1'},
                                        {'label': translater.dic["mild_COAD"][flag], 'value': '2'},
                                        {'label': translater.dic["moderate_COAD"][flag], 'value': '3'},
                                        {'label': translater.dic["dyspnea_rest"][flag], 'value': '4'}
                                    ],
                                    value='1',
                                    id="home-17"
                                ),

                                html.H6(translater.dic["ARISCAT_preoperative_anemia"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["no"][flag], 'value': '1'},
                                        {'label': translater.dic["yes"][flag], 'value': '2'},
                                    ],
                                    value='1',
                                    id="home-18"
                                ),

                                html.H6(translater.dic["PP_arterial_pulse"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': '50-80 bpm', 'value': '1'},
                                        {'label': '40-49 ou 81-100 bpm', 'value': '2'},
                                        {'label': '101-120 bpm', 'value': '3'},
                                        {'label': '<40 ou >120 bpm', 'value': '4'}
                                    ],
                                    value='1',
                                    id="home-19"
                                ),

                                html.H6(translater.dic["PP_procedures"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["one"][flag], 'value': '1'},
                                        {'label': translater.dic["two"][flag], 'value': '2'},
                                        {'label': translater.dic["three"][flag], 'value': '3'}
                                    ],
                                    value='1',
                                    id="home-20"
                                )
                            ]), width=4),
                        ], justify="center"),
                        html.Div(style={"height": 50}),
                        dbc.Row(dbc.Button(translater.dic["calculate"][flag], id='submit-home', key=flag, color="success",
                                           style={"fontSize": 20, "height": 60, "width": 180}),
                                justify="center"),
                        html.Div(id="output-home",
                                 style={'display': 'flex', 'align-items': 'center', 'justify-content': 'center', 'text-align': 'center'})
                    ])]),
            dcc.Tab(label=translater.dic["complications"][flag],
                    children=[html.Div([
                        html.Div(style={"height": 50}),
                        dbc.Row([
                            dbc.Col(html.Div([
                                html.H6(translater.dic["PP_peritoneal_contamination"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["no_contamination"][flag], 'value': '1'},
                                        {'label': translater.dic["minimal_contamination"][flag], 'value': '2'},
                                        {'label': translater.dic["local_pus"][flag], 'value': '3'},
                                        {'label': translater.dic["free_contents"][flag], 'value': '4'}
                                    ],
                                    value='1',
                                    id="complications-1"
                                ),

                                html.H6(translater.dic["ACS_functional_status"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["independent"][flag], 'value': '1'},
                                        {'label': translater.dic["partially_dependent"][flag], 'value': '2'},
                                        {'label': translater.dic["fully_dependent"][flag], 'value': '3'}
                                    ],
                                    value='1',
                                    id="complications-2",
                                ),

                                html.H6(translater.dic["ACS_systemic_sepsis"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["none"][flag], 'value': '1'},
                                        {'label': translater.dic["sirs"][flag], 'value': '2'},
                                        {'label': translater.dic["sepsis"][flag], 'value': '3'},
                                        {'label': translater.dic["septic_shock"][flag], 'value': '4'}
                                    ],
                                    value='1',
                                    id="complications-3",
                                ),

                                html.H6('ASA'),
                                dcc.Dropdown(
                                    options=[
                                        {'label': 'ASA 1', 'value': '1'},
                                        {'label': 'ASA 2', 'value': '2'},
                                        {'label': 'ASA 3', 'value': '3'},
                                        {'label': 'ASA 4', 'value': '4'},
                                        {'label': 'ASA 5', 'value': '5'}
                                    ],
                                    value='1',
                                    id="complications-4"
                                ),
                            ]), width=4),
                            dbc.Col(html.Div([
                                html.H6(translater.dic["PP_procedures"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["one"][flag], 'value': '1'},
                                        {'label': translater.dic["two"][flag], 'value': '2'},
                                        {'label': translater.dic["three"][flag], 'value': '3'}
                                    ],
                                    value='1',
                                    id="complications-5"
                                ),

                                html.H6(translater.dic["PP_hemoglobin"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': '13-16 g/dL', 'value': '1'},
                                        {'label': '11.5-12.9 ou 16.1-17 g/dL', 'value': '2'},
                                        {'label': '10-11.4 ou 17.1-18 g/dL', 'value': '3'},
                                        {'label': '<10 ou >18 g/dL', 'value': '4'}
                                    ],
                                    value='1',
                                    id="complications-6"
                                ),

                                html.H6(translater.dic["PP_respiratory"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["no_dyspnea"][flag], 'value': '1'},
                                        {'label': translater.dic["mild_COAD"][flag], 'value': '2'},
                                        {'label': translater.dic["moderate_COAD"][flag], 'value': '3'},
                                        {'label': translater.dic["dyspnea_rest"][flag], 'value': '4'}
                                    ],
                                    value='1',
                                    id="complications-7"
                                ),

                                html.H6(translater.dic["ACS_dyspnea"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["no"][flag], 'value': '1'},
                                        {'label': translater.dic["moderate_effort"][flag], 'value': '2'},
                                        {'label': translater.dic["at_rest"][flag], 'value': '3'},
                                    ],
                                    value='1',
                                    id="complications-8",
                                )
                            ]), width=4),
                        ], justify="center"),
                        html.Div(style={"height": 50}),
                        dbc.Row(dbc.Button(translater.dic["calculate"][flag], id='submit-complications', key=flag, color="success",
                                           style={"fontSize": 20, "height": 60, "width": 180}),
                                justify="center"),
                        html.Div(id="output-complications",
                                 style={'display': 'flex', 'align-items': 'center', 'justify-content': 'center', 'text-align': 'center'})
                    ])]),
            dcc.Tab(label=translater.dic["severity"][flag],
                    children=[html.Div([
                        html.Div(style={"height": 50}),
                        dbc.Row([
                            dbc.Col(html.Div([
                                html.H6(translater.dic["ACS_systemic_sepsis"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["none"][flag], 'value': '1'},
                                        {'label': translater.dic["sirs"][flag], 'value': '2'},
                                        {'label': translater.dic["sepsis"][flag], 'value': '3'},
                                        {'label': translater.dic["septic_shock"][flag], 'value': '4'}
                                    ],
                                    value='1',
                                    id="severity-1",
                                ),

                                html.H6(translater.dic["ACS_functional_status"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["independent"][flag], 'value': '1'},
                                        {'label': translater.dic["partially_dependent"][flag], 'value': '2'},
                                        {'label': translater.dic["fully_dependent"][flag], 'value': '3'}
                                    ],
                                    value='1',
                                    id="severity-2",
                                ),

                                html.H6(translater.dic["PP_peritoneal_contamination"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["no_contamination"][flag], 'value': '1'},
                                        {'label': translater.dic["minimal_contamination"][flag], 'value': '2'},
                                        {'label': translater.dic["local_pus"][flag], 'value': '3'},
                                        {'label': translater.dic["free_contents"][flag], 'value': '4'}
                                    ],
                                    value='1',
                                    id="severity-3"
                                ),

                                html.H6('ASA'),
                                dcc.Dropdown(
                                    options=[
                                        {'label': 'ASA 1', 'value': '1'},
                                        {'label': 'ASA 2', 'value': '2'},
                                        {'label': 'ASA 3', 'value': '3'},
                                        {'label': 'ASA 4', 'value': '4'},
                                        {'label': 'ASA 5', 'value': '5'}
                                    ],
                                    value='1',
                                    id="severity-4"
                                ),

                                html.H6(translater.dic["PP_procedures"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["one"][flag], 'value': '1'},
                                        {'label': translater.dic["two"][flag], 'value': '2'},
                                        {'label': translater.dic["three"][flag], 'value': '3'}
                                    ],
                                    value='1',
                                    id="severity-5"
                                ),

                                html.H6(translater.dic["PP_leukocytes"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': '4-10', 'value': '1'},
                                        {'label': '10.1-20 ou 3.1-4', 'value': '2'},
                                        {'label': '>20 ou <3', 'value': '3'}
                                    ],
                                    value='1',
                                    id="severity-6"
                                ),

                                html.H6(translater.dic["PP_hemoglobin"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': '13-16 g/dL', 'value': '1'},
                                        {'label': '11.5-12.9 ou 16.1-17 g/dL', 'value': '2'},
                                        {'label': '10-11.4 ou 17.1-18 g/dL', 'value': '3'},
                                        {'label': '<10 ou >18 g/dL', 'value': '4'}
                                    ],
                                    value='1',
                                    id="severity-7"
                                ),

                                html.H6(translater.dic["PP_respiratory"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["no_dyspnea"][flag], 'value': '1'},
                                        {'label': translater.dic["mild_COAD"][flag], 'value': '2'},
                                        {'label': translater.dic["moderate_COAD"][flag], 'value': '3'},
                                        {'label': translater.dic["dyspnea_rest"][flag], 'value': '4'}
                                    ],
                                    value='1',
                                    id="severity-8"
                                ),
                            ]), width=4),
                            dbc.Col(html.Div([
                                html.H6(translater.dic["ACS_dyspnea"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["no"][flag], 'value': '1'},
                                        {'label': translater.dic["moderate_effort"][flag], 'value': '2'},
                                        {'label': translater.dic["at_rest"][flag], 'value': '3'},
                                    ],
                                    value='1',
                                    id="severity-9",
                                ),

                                html.H6(translater.dic["PP_arterial_pulse"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': '50-80 bpm', 'value': '1'},
                                        {'label': '40-49 ou 81-100 bpm', 'value': '2'},
                                        {'label': '101-120 bpm', 'value': '3'},
                                        {'label': '<40 ou >120 bpm', 'value': '4'}
                                    ],
                                    value='1',
                                    id="severity-10"
                                ),

                                html.H6(translater.dic["PP_sodium"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': '>135 mmol/L', 'value': '1'},
                                        {'label': '131-135 mmol/L', 'value': '2'},
                                        {'label': '126-130 mmol/L', 'value': '3'},
                                        {'label': '<126 mmol/L', 'value': '4'}
                                    ],
                                    value='4',
                                    id="severity-11"
                                ),

                                html.H6('PP ECG'),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["normal_ECG"][flag], 'value': '1'},
                                        {'label': translater.dic["ECG_AF"][flag], 'value': '2'},
                                        {'label': translater.dic["abnormal_rhythm"][flag], 'value': '3'}
                                    ],
                                    value='1',
                                    id="severity-12"
                                ),

                                html.H6(translater.dic["PP_urea"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': '<7.6', 'value': '1'},
                                        {'label': '7.6-10', 'value': '2'},
                                        {'label': '10.1-15', 'value': '3'},
                                        {'label': '>15', 'value': '4'}
                                    ],
                                    value='1',
                                    id="severity-13"
                                ),

                                html.H6(translater.dic["ARISCAT_emergent_procedure"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["no"][flag], 'value': '1'},
                                        {'label': translater.dic["yes"][flag], 'value': '2'}
                                    ],
                                    value='1',
                                    id="severity-14",
                                ),

                                html.H6(translater.dic["ARISCAT_preoperative_anemia"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["no"][flag], 'value': '1'},
                                        {'label': translater.dic["yes"][flag], 'value': '2'},
                                    ],
                                    value='1',
                                    id="severity-15",
                                )
                            ]), width=4),
                        ], justify="center"),
                        html.Div(style={"height": 50}),
                        dbc.Row(dbc.Button(translater.dic["calculate"][flag], id='submit-severity', key=flag, color="success",
                                           style={"fontSize": 20, "height": 60, "width": 180}),
                                justify="center"),
                        html.Div(id="output-severity",
                                 style={'display': 'flex', 'align-items': 'center', 'justify-content': 'center', 'text-align': 'center'})
                    ])]),
            dcc.Tab(label=translater.dic["uci_days"][flag],
                    children=[html.Div([
                        html.Div(style={"height": 50}),
                        dbc.Row([
                            dbc.Col(html.Div([
                                html.H6(translater.dic["PP_procedures"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["one"][flag], 'value': '1'},
                                        {'label': translater.dic["two"][flag], 'value': '2'},
                                        {'label': translater.dic["three"][flag], 'value': '3'}
                                    ],
                                    value='1',
                                    id="uci-1"
                                ),

                                html.H6(translater.dic["ARISCAT_surgery_duration"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["<2_hours"][flag], 'value': '1'},
                                        {'label': translater.dic["between_2_3_hours"][flag], 'value': '2'},
                                        {'label': translater.dic[">3_hours"][flag], 'value': '3'},
                                    ],
                                    value='1',
                                    id="uci-2",
                                ),

                                html.H6(translater.dic["ACS_systemic_sepsis"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["none"][flag], 'value': '1'},
                                        {'label': translater.dic["sirs"][flag], 'value': '2'},
                                        {'label': translater.dic["sepsis"][flag], 'value': '3'},
                                        {'label': translater.dic["septic_shock"][flag], 'value': '4'}
                                    ],
                                    value='1',
                                    id="uci-3",
                                ),

                                html.H6(translater.dic["ARISCAT_respiratory_infection"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["yes"][flag], 'value': '2'},
                                        {'label': translater.dic["no"][flag], 'value': '1'},
                                    ],
                                    value='2',
                                    id="uci-4"
                                ),
                            ]), width=4),
                            dbc.Col(html.Div([
                                html.H6(translater.dic["ARISCAT_emergent_procedure"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["no"][flag], 'value': '1'},
                                        {'label': translater.dic["yes"][flag], 'value': '2'}
                                    ],
                                    value='1',
                                    id="uci-5",
                                ),

                                html.H6(translater.dic["ARISCAT_preoperative_anemia"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["no"][flag], 'value': '1'},
                                        {'label': translater.dic["yes"][flag], 'value': '2'},
                                    ],
                                    value='1',
                                    id="uci-6",
                                ),

                                html.H6(translater.dic["ACS_acute_renal_failure"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["no"][flag], 'value': '1'},
                                        {'label': translater.dic["yes"][flag], 'value': '2'}
                                    ],
                                    value='1',
                                    id="uci-7",
                                ),
                            ]), width=4),
                        ], justify="center"),
                        html.Div(style={"height": 50}),
                        dbc.Row(dbc.Button(translater.dic["calculate"][flag], id='submit-uci', key=flag, color="success",
                                           style={"fontSize": 20, "height": 60, "width": 180}),
                                justify="center"),
                        html.Div(id="output-uci",
                                 style={'display': 'flex', 'align-items': 'center', 'justify-content': 'center', 'text-align': 'center'})
                    ])]),
            dcc.Tab(label=translater.dic["ipop_days"][flag],
                    children=[html.Div([
                        html.Div(style={"height": 50}),
                        dbc.Row([
                            dbc.Col(html.Div([
                                html.H6(translater.dic["PP_procedures"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["one"][flag], 'value': '1'},
                                        {'label': translater.dic["two"][flag], 'value': '2'},
                                        {'label': translater.dic["three"][flag], 'value': '3'}
                                    ],
                                    value='1',
                                    id="ipop-1"
                                ),

                                html.H6(translater.dic["ACS_systemic_sepsis"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["none"][flag], 'value': '1'},
                                        {'label': translater.dic["sirs"][flag], 'value': '2'},
                                        {'label': translater.dic["sepsis"][flag], 'value': '3'},
                                        {'label': translater.dic["septic_shock"][flag], 'value': '4'}
                                    ],
                                    value='1',
                                    id="ipop-2",

                                ),

                                html.H6(translater.dic["PP_peritoneal_contamination"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["no_contamination"][flag], 'value': '1'},
                                        {'label': translater.dic["minimal_contamination"][flag], 'value': '2'},
                                        {'label': translater.dic["local_pus"][flag], 'value': '3'},
                                        {'label': translater.dic["free_contents"][flag], 'value': '4'}
                                    ],
                                    value='1',
                                    id="ipop-3"
                                ),

                                html.H6(translater.dic["ACS_functional_status"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["independent"][flag], 'value': '1'},
                                        {'label': translater.dic["partially_dependent"][flag], 'value': '2'},
                                        {'label': translater.dic["fully_dependent"][flag], 'value': '3'}
                                    ],
                                    value='1',
                                    id="ipop-4",
                                ),

                                html.H6(translater.dic["PP_hemoglobin"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': '13-16 g/dL', 'value': '1'},
                                        {'label': '11.5-12.9 ou 16.1-17 g/dL', 'value': '2'},
                                        {'label': '10-11.4 ou 17.1-18 g/dL', 'value': '3'},
                                        {'label': '<10 ou >18 g/dL', 'value': '4'}
                                    ],
                                    value='1',
                                    id="ipop-5"
                                ),

                                html.H6(translater.dic["PP_leukocytes"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': '4-10', 'value': '1'},
                                        {'label': '10.1-20 ou 3.1-4', 'value': '2'},
                                        {'label': '>20 ou <3', 'value': '3'}
                                    ],
                                    value='1',
                                    id="ipop-6"
                                ),
                            ]), width=4),
                            dbc.Col(html.Div([
                                html.H6(translater.dic["ARISCAT_surgery_duration"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["<2_hours"][flag], 'value': '1'},
                                        {'label': translater.dic["between_2_3_hours"][flag], 'value': '2'},
                                        {'label': translater.dic[">3_hours"][flag], 'value': '3'},
                                    ],
                                    value='1',
                                    id="ipop-7",
                                ),

                                html.H6(translater.dic["PP_sodium"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': '>135 mmol/L', 'value': '1'},
                                        {'label': '131-135 mmol/L', 'value': '2'},
                                        {'label': '126-130 mmol/L', 'value': '3'},
                                        {'label': '<126 mmol/L', 'value': '4'}
                                    ],
                                    value='4',
                                    id="ipop-8"
                                ),

                                html.H6(translater.dic["PP_respiratory"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["no_dyspnea"][flag], 'value': '1'},
                                        {'label': translater.dic["mild_COAD"][flag], 'value': '2'},
                                        {'label': translater.dic["moderate_COAD"][flag], 'value': '3'},
                                        {'label': translater.dic["dyspnea_rest"][flag], 'value': '4'}
                                    ],
                                    value='1',
                                    id="ipop-9"
                                ),

                                html.H6(translater.dic["ACS_dyspnea"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["no"][flag], 'value': '1'},
                                        {'label': translater.dic["moderate_effort"][flag], 'value': '2'},
                                        {'label': translater.dic["at_rest"][flag], 'value': '3'},
                                    ],
                                    value='1',
                                    id="ipop-10",
                                ),

                                html.H6(translater.dic["PP_urea"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': '<7.6', 'value': '1'},
                                        {'label': '7.6-10', 'value': '2'},
                                        {'label': '10.1-15', 'value': '3'},
                                        {'label': '>15', 'value': '4'}
                                    ],
                                    value='1',
                                    id="ipop-11"
                                )
                            ]), width=4),
                        ], justify="center"),
                        html.Div(style={"height": 50}),
                        dbc.Row(dbc.Button(translater.dic["calculate"][flag], id='submit-ipop', key=flag, color="success",
                                           style={"fontSize": 20, "height": 60, "width": 180}),
                                justify="center"),
                        html.Div(id="output-ipop",
                                 style={'display': 'flex', 'align-items': 'center', 'justify-content': 'center', 'text-align': 'center'})
                    ])]),
            dcc.Tab(label=translater.dic["death_1_year"][flag],
                    children=[html.Div([
                        html.Div(style={"height": 50}),
                        dbc.Row([
                            dbc.Col(html.Div([
                                html.H6(translater.dic["ACS_functional_status"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["independent"][flag], 'value': '1'},
                                        {'label': translater.dic["partially_dependent"][flag], 'value': '2'},
                                        {'label': translater.dic["fully_dependent"][flag], 'value': '3'}
                                    ],
                                    value='1',
                                    id="death-1-year-1",
                                ),

                                html.H6('ASA'),
                                dcc.Dropdown(
                                    options=[
                                        {'label': 'ASA 1', 'value': '1'},
                                        {'label': 'ASA 2', 'value': '2'},
                                        {'label': 'ASA 3', 'value': '3'},
                                        {'label': 'ASA 4', 'value': '4'},
                                        {'label': 'ASA 5', 'value': '5'}
                                    ],
                                    value='1',
                                    id="death-1-year-2"
                                ),

                                html.H6(translater.dic["PP_hemoglobin"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': '13-16 g/dL', 'value': '1'},
                                        {'label': '11.5-12.9 ou 16.1-17 g/dL', 'value': '2'},
                                        {'label': '10-11.4 ou 17.1-18 g/dL', 'value': '3'},
                                        {'label': '<10 ou >18 g/dL', 'value': '4'}
                                    ],
                                    value='1',
                                    id="death-1-year-3"
                                ),
                                html.H6(translater.dic["PP_status_of_malignancy"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["no_malignancy"][flag], 'value': '1'},
                                        {'label': translater.dic["primary_malignancy"][flag], 'value': '2'},
                                        {'label': translater.dic["nodal_metastases"][flag], 'value': '3'},
                                        {'label': translater.dic["distant_metastasis"][flag], 'value': '4'}
                                    ],
                                    value='1',
                                    id="death-1-year-4"
                                ),
                            ]), width=4),
                            dbc.Col(html.Div([
                                html.H6(translater.dic["ACS_weight"][flag]),
                                dcc.Input(id='death-1-year-5', type='text'),

                                html.H6(translater.dic["PP_peritoneal_contamination"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["no_contamination"][flag], 'value': '1'},
                                        {'label': translater.dic["minimal_contamination"][flag], 'value': '2'},
                                        {'label': translater.dic["local_pus"][flag], 'value': '3'},
                                        {'label': translater.dic["free_contents"][flag], 'value': '4'}
                                    ],
                                    value='1',
                                    id="death-1-year-6"
                                ),

                                html.H6(translater.dic["ACS_systemic_sepsis"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["none"][flag], 'value': '1'},
                                        {'label': translater.dic["sirs"][flag], 'value': '2'},
                                        {'label': translater.dic["sepsis"][flag], 'value': '3'},
                                        {'label': translater.dic["septic_shock"][flag], 'value': '4'}
                                    ],
                                    value='1',
                                    id="death-1-year-7",
                                )
                            ]), width=4),
                        ], justify="center"),
                        html.Div(style={"height": 50}),
                        dbc.Row(dbc.Button(translater.dic["calculate"][flag], id='submit-death-1-year', key=flag, color="success",
                                           style={"fontSize": 20, "height": 60, "width": 180}),
                                justify="center"),
                        html.Div(id="output-death-1-year",
                                 style={'display': 'flex', 'align-items': 'center', 'justify-content': 'center', 'text-align': 'center'})
                    ])]),
            dcc.Tab(label=translater.dic["death_months"][flag],
                    children=[html.Div([
                        html.Div(style={"height": 50}),
                        dbc.Row([
                            dbc.Col(html.Div([
                                html.H6(translater.dic["ACS_functional_status"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["independent"][flag], 'value': '1'},
                                        {'label': translater.dic["partially_dependent"][flag], 'value': '2'},
                                        {'label': translater.dic["fully_dependent"][flag], 'value': '3'}
                                    ],
                                    value='1',
                                    id="death-months-1",
                                ),

                                html.H6('ASA'),
                                dcc.Dropdown(
                                    options=[
                                        {'label': 'ASA 1', 'value': '1'},
                                        {'label': 'ASA 2', 'value': '2'},
                                        {'label': 'ASA 3', 'value': '3'},
                                        {'label': 'ASA 4', 'value': '4'},
                                        {'label': 'ASA 5', 'value': '5'}
                                    ],
                                    value='1',
                                    id="death-months-2"
                                ),

                                html.H6(translater.dic["ACS_systemic_sepsis"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["none"][flag], 'value': '1'},
                                        {'label': translater.dic["sirs"][flag], 'value': '2'},
                                        {'label': translater.dic["sepsis"][flag], 'value': '3'},
                                        {'label': translater.dic["septic_shock"][flag], 'value': '4'}
                                    ],
                                    value='1',
                                    id="death-months-3",
                                ),
                            ]), width=4),
                            dbc.Col(html.Div([
                                html.H6(translater.dic["PP_peritoneal_contamination"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["no_contamination"][flag], 'value': '1'},
                                        {'label': translater.dic["minimal_contamination"][flag], 'value': '2'},
                                        {'label': translater.dic["local_pus"][flag], 'value': '3'},
                                        {'label': translater.dic["free_contents"][flag], 'value': '4'}
                                    ],
                                    value='1',
                                    id="death-months-4"
                                ),

                                html.H6(translater.dic["PP_leukocytes"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': '4-10', 'value': '1'},
                                        {'label': '10.1-20 ou 3.1-4', 'value': '2'},
                                        {'label': '>20 ou <3', 'value': '3'}
                                    ],
                                    value='1',
                                    id="death-months-5"
                                ),

                                html.H6(translater.dic["PP_status_of_malignancy"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["no_malignancy"][flag], 'value': '1'},
                                        {'label': translater.dic["primary_malignancy"][flag], 'value': '2'},
                                        {'label': translater.dic["nodal_metastases"][flag], 'value': '3'},
                                        {'label': translater.dic["distant_metastasis"][flag], 'value': '4'}
                                    ],
                                    value='1',
                                    id="death-months-6"
                                ),
                            ]), width=4),
                        ], justify="center"),
                        html.Div(style={"height": 50}),
                        dbc.Row(dbc.Button(translater.dic["calculate"][flag], id='submit-death-months', key=flag, color="success",
                                           style={"fontSize": 20, "height": 60, "width": 180}),
                                justify="center"),
                        html.Div(id="output-death-months",
                                 style={'display': 'flex', 'align-items': 'center', 'justify-content': 'center', 'text-align': 'center'})
                    ])]),
            dcc.Tab(label=translater.dic["nas"][flag],
                    children=[html.Div([
                        html.Div(style={"height": 50}),
                        dbc.Row([
                            dbc.Col(html.Div([
                                html.H6(translater.dic["ARISCAT_surgery_duration"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["<2_hours"][flag], 'value': '1'},
                                        {'label': translater.dic["between_2_3_hours"][flag], 'value': '2'},
                                        {'label': translater.dic[">3_hours"][flag], 'value': '3'},
                                    ],
                                    value='1',
                                    id="nas-1",
                                ),
                                html.H6(translater.dic["PP_procedures"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["one"][flag], 'value': '1'},
                                        {'label': translater.dic["two"][flag], 'value': '2'},
                                        {'label': translater.dic["three"][flag], 'value': '3'}
                                    ],
                                    value='1',
                                    id="nas-2"
                                ),
                            ]), width=4),
                            dbc.Col(html.Div([
                                html.H6(translater.dic["ACS_systemic_sepsis"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': translater.dic["none"][flag], 'value': '1'},
                                        {'label': translater.dic["sirs"][flag], 'value': '2'},
                                        {'label': translater.dic["sepsis"][flag], 'value': '3'},
                                        {'label': translater.dic["septic_shock"][flag], 'value': '4'}
                                    ],
                                    value='1',
                                    id="nas-3",
                                ),

                                html.H6(translater.dic["PP_leukocytes"][flag]),
                                dcc.Dropdown(
                                    options=[
                                        {'label': '4-10', 'value': '1'},
                                        {'label': '10.1-20 ou 3.1-4', 'value': '2'},
                                        {'label': '>20 ou <3', 'value': '3'}
                                    ],
                                    value='1',
                                    id="nas-4"
                                ),
                            ]), width=4),
                        ], justify="center"),
                        html.Div(style={"height": 50}),
                        dbc.Row(dbc.Button(translater.dic["calculate"][flag], id='submit-nas', key=flag, color="success",
                                           style={"fontSize": 20, "height": 60, "width": 180}),
                                justify="center"),
                        html.Div(id="output-nas",
                                 style={'display': 'flex', 'align-items': 'center', 'justify-content': 'center', 'text-align': 'center'})
                    ])]),
        ], style={
            'width': '100%',
            'fontSize': 20,
            'margin-left': 'auto',
            'margin-right': 'auto',
            'fontWeight': 'bold'}),

    ], id="root")


@app.callback(Output(component_id='root', component_property='children'),
              [Input(component_id='en_lang', component_property='n_clicks'),
               Input(component_id='pt_lang', component_property='n_clicks')])
def get_page_lang(n_clicks_en, n_clicks_pt):
    if (n_clicks_en is None) and (n_clicks_pt is None):
        raise PreventUpdate
    else:
        ctx = dash.callback_context
        btn_clicked = ctx.triggered[0]['prop_id'].split('.')[0]
        if btn_clicked == 'pt_lang':
            flag = 0
            return html.Div(page(flag))
        elif btn_clicked == 'en_lang':
            flag = 1
            return html.Div(page(flag))


@app.callback(Output(component_id='output-home', component_property='children'),
              Input(component_id='submit-home', component_property='n_clicks'),
              Input(component_id='submit-home', component_property='key'),
              state=[State(component_id="home-1", component_property='value'),
                     State(component_id="home-2", component_property='value'),
                     State(component_id="home-3", component_property='value'),
                     State(component_id="home-4", component_property='value'),
                     State(component_id="home-5", component_property='value'),
                     State(component_id="home-6", component_property='value'),
                     State(component_id="home-7", component_property='value'),
                     State(component_id="home-8", component_property='value'),
                     State(component_id="home-9", component_property='value'),
                     State(component_id="home-10", component_property='value'),
                     State(component_id="home-11", component_property='value'),
                     State(component_id="home-12", component_property='value'),
                     State(component_id="home-13", component_property='value'),
                     State(component_id="home-14", component_property='value'),
                     State(component_id="home-15", component_property='value'),
                     State(component_id="home-16", component_property='value'),
                     State(component_id="home-17", component_property='value'),
                     State(component_id="home-18", component_property='value'),
                     State(component_id="home-19", component_property='value'),
                     State(component_id="home-20", component_property='value')
                     ])
def update_result_home(n_clicks, flag, value1, value2, value3, value4, value5, value6, value7,
                       value8, value9, value10, value11, value12, value13, value14,
                       value15, value16, value17, value18, value19, value20):
    if n_clicks is None:
        raise PreventUpdate
    else:
        # Predictions: Complications
        fig_complications, message_complications = predict_complications(flag, value1, value2, value3, value4, value5, value6, value7, value8)

        # Predictions: Severity
        fig_severity, message_severity = predict_severity(flag, value1, value10, value11, value12, value13, value14, value15, value2, value3, value4,
                                                          value5, value6, value7, value8, value9)

        # Predictions: UCI
        fig_uci, fig_uci_2, message_uci, pred_uci = predict_days_uci(flag, value1, value2, value3, value4, value5, value6, value7)

        # Predictions: IPOP
        fig_ipop, fig_ipop_2, message_ipop, pred_ipop = predict_days_ipop(flag, value1, value10, value11, value2, value3, value4, value5, value6,
                                                                          value7, value8, value9)

        # Predictions: Death 1 year
        fig_death_1_year, message_death_1_year = predict_death_1_year(flag, value1, value2, value3, value4, value5, value6, value7)

        # Predictions: Death Months
        fig_death_months, message_death_months = predict_death_months(flag, value1, value2, value3, value4, value5, value6)

        # Predictions: NAS
        fig_nas, fig_nas_2, message_nas, pred_nas = predict_nas(flag, value1, value2, value3, value4)

        return html.Div([
            dbc.Row([
                dbc.Col(html.Div([
                    html.Hr(),
                    html.H6(translater.dic["result"][flag] + message_complications),
                    html.Hr(),
                ]), width=4),
                dbc.Col(html.Div([
                    dcc.Graph(figure=fig_complications, style={'width': 700})
                ]), width=4),
            ], justify="center"),
            dbc.Row([
                dbc.Col(html.Div([
                    html.Hr(),
                    html.H6(translater.dic["result"][flag] + message_severity),
                    html.H6(translater.dic["clavien_dindo_scale"][flag]),
                    html.Hr(),
                ]), width=4),
                dbc.Col(html.Div([
                    dcc.Graph(figure=fig_severity, style={'width': 700})
                ]), width=4),
            ], justify="center"),
            dbc.Row([
                dbc.Col(html.Div([
                    html.Hr(),
                    html.H6(translater.dic["result"][flag] + message_uci),
                    html.H6(translater.dic["prediction"][flag] + str(pred_uci) + " ± 1"),
                    html.Hr(),
                ]), width=4),
                dbc.Col(html.Div([
                    dcc.Graph(figure=fig_uci, style={'width': 700}),
                    dcc.Graph(figure=fig_uci_2, style={'width': 700})
                ]), width=4),
            ], justify="center"),
            dbc.Row([
                dbc.Col(html.Div([
                    html.Hr(),
                    html.H6(translater.dic["result"][flag] + message_ipop),
                    html.H6(translater.dic["prediction"][flag] + str(pred_ipop) + " ± 10"),
                    html.Hr(),
                ]), width=4),
                dbc.Col(html.Div([
                    dcc.Graph(figure=fig_ipop, style={'width': 700}),
                    dcc.Graph(figure=fig_ipop_2, style={'width': 700})
                ]), width=4),
            ], justify="center"),
            dbc.Row([
                dbc.Col(html.Div([
                    html.Hr(),
                    html.H6(translater.dic["result"][flag] + message_death_1_year),
                    html.Hr(),
                ]), width=4),
                dbc.Col(html.Div([
                    dcc.Graph(figure=fig_death_1_year, style={'width': 700})
                ]), width=4),
            ], justify="center"),
            dbc.Row([
                dbc.Col(html.Div([
                    html.Hr(),
                    html.H6(translater.dic["result"][flag] + message_death_months),
                    html.Hr(),
                ]), width=4),
                dbc.Col(html.Div([
                    dcc.Graph(figure=fig_death_months, style={'width': 700})
                ]), width=4),
            ], justify="center"),
            dbc.Row([
                dbc.Col(html.Div([
                    html.Hr(),
                    html.H6(translater.dic["result"][flag] + message_nas),
                    html.H6(translater.dic["prediction"][flag] + str(pred_nas) + " ± 74"),
                    html.Hr(),
                ]), width=4),
                dbc.Col(html.Div([
                    dcc.Graph(figure=fig_nas, style={'width': 700}),
                    dcc.Graph(figure=fig_nas_2, style={'width': 700})
                ]), width=4),
            ], justify="center"),
        ])


@app.callback(Output(component_id='output-complications', component_property='children'),
              Input(component_id='submit-complications', component_property='n_clicks'),
              Input(component_id='submit-complications', component_property='key'),
              state=[State(component_id="complications-1", component_property='value'),
                     State(component_id="complications-2", component_property='value'),
                     State(component_id="complications-3", component_property='value'),
                     State(component_id="complications-4", component_property='value'),
                     State(component_id="complications-5", component_property='value'),
                     State(component_id="complications-6", component_property='value'),
                     State(component_id="complications-7", component_property='value'),
                     State(component_id="complications-8", component_property='value')])
def update_result_complications(n_clicks, flag, value1, value2, value3, value4, value5, value6, value7, value8):
    if n_clicks is None:
        raise PreventUpdate
    else:
        # Predictions: Complications
        fig, message = predict_complications(flag, value1, value2, value3, value4, value5, value6, value7, value8)

        return html.Div([
            html.Div([
                html.Hr(),
                html.H6(translater.dic["result"][flag] + message),
                html.Hr(),
            ], style={"height": 150}),
            dcc.Graph(figure=fig, style={'width': 700})
        ])


@app.callback(Output(component_id='output-severity', component_property='children'),
              Input(component_id='submit-severity', component_property='n_clicks'),
              Input(component_id='submit-severity', component_property='key'),
              state=[State(component_id="severity-1", component_property='value'),
                     State(component_id="severity-2", component_property='value'),
                     State(component_id="severity-3", component_property='value'),
                     State(component_id="severity-4", component_property='value'),
                     State(component_id="severity-5", component_property='value'),
                     State(component_id="severity-6", component_property='value'),
                     State(component_id="severity-7", component_property='value'),
                     State(component_id="severity-8", component_property='value'),
                     State(component_id="severity-9", component_property='value'),
                     State(component_id="severity-10", component_property='value'),
                     State(component_id="severity-11", component_property='value'),
                     State(component_id="severity-12", component_property='value'),
                     State(component_id="severity-13", component_property='value'),
                     State(component_id="severity-14", component_property='value'),
                     State(component_id="severity-15", component_property='value')])
def update_result_severity(n_clicks, flag, value1, value2, value3, value4, value5, value6, value7,
                           value8, value9, value10, value11, value12, value13, value14, value15):
    if n_clicks is None:
        raise PreventUpdate
    else:
        # Predictions: Severity
        fig, message = predict_severity(flag, value1, value10, value11, value12, value13, value14, value15, value2, value3, value4,
                                        value5, value6, value7, value8, value9)

        return html.Div([
            html.Div([
                html.Hr(),
                html.H6(translater.dic["result"][flag] + message),
                html.H6(translater.dic["clavien_dindo_scale"][flag]),
                html.Hr(),
            ], style={"height": 150}),
            dcc.Graph(figure=fig, style={'width': 700})
        ])


@app.callback(Output(component_id='output-uci', component_property='children'),
              Input(component_id='submit-uci', component_property='n_clicks'),
              Input(component_id='submit-uci', component_property='key'),
              state=[State(component_id="uci-1", component_property='value'),
                     State(component_id="uci-2", component_property='value'),
                     State(component_id="uci-3", component_property='value'),
                     State(component_id="uci-4", component_property='value'),
                     State(component_id="uci-5", component_property='value'),
                     State(component_id="uci-6", component_property='value'),
                     State(component_id="uci-7", component_property='value')])
def update_result_uci(n_clicks, flag, value1, value2, value3, value4, value5, value6, value7):
    if n_clicks is None:
        raise PreventUpdate
    else:
        # Predictions: UCI
        fig, fig2, message, pred = predict_days_uci(flag, value1, value2, value3, value4, value5, value6, value7)

        return html.Div([
            html.Div([
                html.Hr(),
                html.H6(translater.dic["result"][flag] + message),
                html.H6(translater.dic["prediction"][flag] + str(pred) + " ± 1"),
                html.Hr(),
            ], style={"height": 150}),
            dcc.Graph(figure=fig, style={'width': 700}),
            dcc.Graph(figure=fig2, style={'width': 700})
        ])


@app.callback(Output(component_id='output-ipop', component_property='children'),
              Input(component_id='submit-ipop', component_property='n_clicks'),
              Input(component_id='submit-ipop', component_property='key'),
              state=[State(component_id="ipop-1", component_property='value'),
                     State(component_id="ipop-2", component_property='value'),
                     State(component_id="ipop-3", component_property='value'),
                     State(component_id="ipop-4", component_property='value'),
                     State(component_id="ipop-5", component_property='value'),
                     State(component_id="ipop-6", component_property='value'),
                     State(component_id="ipop-7", component_property='value'),
                     State(component_id="ipop-8", component_property='value'),
                     State(component_id="ipop-9", component_property='value'),
                     State(component_id="ipop-10", component_property='value'),
                     State(component_id="ipop-11", component_property='value')])
def update_result_ipop(n_clicks, flag, value1, value2, value3, value4, value5, value6, value7,
                       value8, value9, value10, value11):
    if n_clicks is None:
        raise PreventUpdate
    else:
        # Predictions: IPOP
        fig, fig2, message, pred = predict_days_ipop(flag, value1, value10, value11, value2, value3, value4, value5, value6, value7, value8, value9)

        return html.Div([
            html.Div([
                html.Hr(),
                html.H6(translater.dic["result"][flag] + message),
                html.H6(translater.dic["prediction"][flag] + str(pred) + " ± 10"),
                html.Hr(),
            ], style={"height": 150}),
            dcc.Graph(figure=fig, style={'width': 700}),
            dcc.Graph(figure=fig2, style={'width': 700})
        ])


@app.callback(Output(component_id='output-death-1-year', component_property='children'),
              Input(component_id='submit-death-1-year', component_property='n_clicks'),
              Input(component_id='submit-death-1-year', component_property='key'),
              state=[State(component_id="death-1-year-1", component_property='value'),
                     State(component_id="death-1-year-2", component_property='value'),
                     State(component_id="death-1-year-3", component_property='value'),
                     State(component_id="death-1-year-4", component_property='value'),
                     State(component_id="death-1-year-5", component_property='value'),
                     State(component_id="death-1-year-6", component_property='value'),
                     State(component_id="death-1-year-7", component_property='value')])
def update_result_death_1_year(n_clicks, flag, value1, value2, value3, value4, value5, value6, value7):
    if n_clicks is None:
        raise PreventUpdate
    else:
        # Predictions: Death 1 year
        fig, message = predict_death_1_year(flag, value1, value2, value3, value4, value5, value6, value7)

        return html.Div([
            html.Div([
                html.Hr(),
                html.H6(translater.dic["result"][flag] + message),
                html.Hr(),
            ], style={"height": 150}),
            dcc.Graph(figure=fig, style={'width': 700})
        ])


@app.callback(Output(component_id='output-death-months', component_property='children'),
              Input(component_id='submit-death-months', component_property='n_clicks'),
              Input(component_id='submit-death-months', component_property='key'),
              state=[State(component_id="death-months-1", component_property='value'),
                     State(component_id="death-months-2", component_property='value'),
                     State(component_id="death-months-3", component_property='value'),
                     State(component_id="death-months-4", component_property='value'),
                     State(component_id="death-months-5", component_property='value'),
                     State(component_id="death-months-6", component_property='value')])
def update_result_death_months(n_clicks, flag, value1, value2, value3, value4, value5, value6):
    if n_clicks is None:
        raise PreventUpdate
    else:
        # Predictions: Death Months
        fig, message = predict_death_months(flag, value1, value2, value3, value4, value5, value6)

        return html.Div([
            html.Div([
                html.Hr(),
                html.H6(translater.dic["result"][flag] + message),
                html.Hr(),
            ], style={"height": 150}),
            dcc.Graph(figure=fig, style={'width': 700})
        ])


@app.callback(Output(component_id='output-nas', component_property='children'),
              Input(component_id='submit-nas', component_property='n_clicks'),
              Input(component_id='submit-nas', component_property='key'),
              state=[State(component_id="nas-1", component_property='value'),
                     State(component_id="nas-2", component_property='value'),
                     State(component_id="nas-3", component_property='value'),
                     State(component_id="nas-4", component_property='value')])
def update_result_nas(n_clicks, flag, value1, value2, value3, value4):
    if n_clicks is None:
        raise PreventUpdate
    else:
        # Predict NAS Points
        fig, fig2, message, pred = predict_nas(flag, value1, value2, value3, value4)

        return html.Div([
            html.Div([
                html.Hr(),
                html.H6(translater.dic["result"][flag] + message),
                html.H6(translater.dic["prediction"][flag] + str(pred) + " ± 74"),
                html.Hr(),
            ], style={"height": 150}),
            dcc.Graph(figure=fig, style={'width': 700}),
            dcc.Graph(figure=fig2, style={'width': 700})
        ])


def predict_complications(flag, value1, value2, value3, value4, value5, value6, value7, value8):
    imp = pickle.load(open("./models/complications_imputer.sav", 'rb'))
    x = imp.transform([[value1, value2, value3, value4, value5, value6, value7, value8]])

    transformer = pickle.load(open("./models/complications_transformer.sav", 'rb'))
    x = transformer.transform(x)

    scaler = pickle.load(open("./models/complications_scaler.sav", 'rb'))
    x = scaler.transform(x)

    model = pickle.load(open("./models/complications_model.sav", 'rb'))
    pred = model.predict(x)
    probability_complications = model.predict_proba(x)

    message_complications = ""
    if pred[0] == 0:
        message_complications = translater.dic["low_probability_complications"][flag]
    else:
        message_complications = translater.dic["high_probability_complications"][flag]

    df = load_and_run.load_data(global_variables.outputs[0],
                                ['PP contaminação peritoneal', 'ACS estado funcional', 'ACS sépsis sistémica', 'ASA', 'PP nº procedimentos',
                                 'PP hemoglobina', 'PP respiratório', 'ACS dispneia'])
    X_graph = df.iloc[:, :-1]
    y_graph = df.iloc[:, -1]
    X_graph = imp.transform(X_graph)
    X_graph = transformer.transform(X_graph)
    X_graph = scaler.transform(X_graph)
    probs = model.predict_proba(X_graph)
    graph_probs = []

    for line in range(0, len(probs)):
        if y_graph.values[line] == 0:
            graph_probs.append([probs[line][0], translater.dic["no"][flag], translater.dic["training_data"][flag]])
        elif y_graph.values[line] == 1:
            graph_probs.append([probs[line][1], translater.dic["yes"][flag], translater.dic["training_data"][flag]])

    graph_probs.append([probability_complications[0][0], translater.dic["no"][flag], translater.dic["current_patient"][flag]])
    graph_probs.append([probability_complications[0][1], translater.dic["yes"][flag], translater.dic["current_patient"][flag]])

    df = pd.DataFrame(graph_probs, columns=[translater.dic["probability"][flag], translater.dic["complications"][flag], 'Tipo'])

    fig_complications = px.violin(df, y=translater.dic["probability"][flag], x=translater.dic["complications"][flag], color='Tipo',
                                  violinmode='overlay',
                                  box=True, points="all", hover_data=df.columns)

    return fig_complications, message_complications


def predict_severity(flag, value1, value10, value11, value12, value13, value14, value15, value2, value3, value4, value5, value6, value7, value8,
                     value9):
    imp = pickle.load(open("./models/severity_imputer.sav", 'rb'))
    x = imp.transform([[value1, value2, value3, value4, value5, value6, value7, value8, value9,
                        value10, value11, value12, value13, value14, value15]])

    transformer = pickle.load(open("./models/severity_transformer.sav", 'rb'))
    x = transformer.transform(x)

    scaler = pickle.load(open("./models/severity_scaler.sav", 'rb'))
    x = scaler.transform(x)

    model = pickle.load(open("./models/severity_model.sav", 'rb'))
    pred = model.predict(x)
    probability = model.predict_proba(x)

    message_severity = ""
    if pred[0] == 1:
        message_severity = translater.dic["complications_i"][flag]
    elif pred[0] == 2:
        message_severity = translater.dic["complications_ii"][flag]
    elif pred[0] == 3:
        message_severity = translater.dic["complications_iii"][flag]
    elif pred[0] == 4:
        message_severity = translater.dic["complications_iv"][flag]

    df = load_and_run.load_data(global_variables.outputs[1],
                                ['ACS sépsis sistémica', 'ACS estado funcional', 'PP contaminação peritoneal', 'ASA', 'PP nº procedimentos',
                                 'PP leucócitos', 'PP hemoglobina', 'PP respiratório', 'ACS dispneia', 'PP pulsação arterial', 'PP sódio',
                                 'PP ECG', 'PP ureia', 'ARISCAT procedimento emergente', 'ARISCAT anemia pré-operativa'])

    X_graph = df.iloc[:, :-1]
    y_graph = df.iloc[:, -1]
    X_graph = imp.transform(X_graph)
    X_graph = transformer.transform(X_graph)
    X_graph = scaler.transform(X_graph)
    probs = model.predict_proba(X_graph)
    graph_probs = []

    for line in range(0, len(probs)):
        if y_graph.values[line] == 1:
            graph_probs.append([probs[line][0], "I", translater.dic["training_data"][flag]])
        elif y_graph.values[line] == 2:
            graph_probs.append([probs[line][1], "II", translater.dic["training_data"][flag]])
        elif y_graph.values[line] == 3:
            graph_probs.append([probs[line][2], "III", translater.dic["training_data"][flag]])
        elif y_graph.values[line] == 4:
            graph_probs.append([probs[line][3], "IV", translater.dic["training_data"][flag]])

    graph_probs.append([probability[0][0], "I", translater.dic["current_patient"][flag]])
    graph_probs.append([probability[0][1], "II", translater.dic["current_patient"][flag]])
    graph_probs.append([probability[0][2], "III", translater.dic["current_patient"][flag]])
    graph_probs.append([probability[0][3], "IV", translater.dic["current_patient"][flag]])

    df = pd.DataFrame(graph_probs, columns=[translater.dic["probability"][flag], translater.dic["severity"][flag], 'Tipo'])

    fig_severity = px.violin(df, y=translater.dic["probability"][flag], x=translater.dic["severity"][flag], color='Tipo', violinmode='overlay',
                             box=True,
                             points="all", hover_data=df.columns)

    return fig_severity, message_severity


def predict_days_uci(flag, value1, value2, value3, value4, value5, value6, value7):
    imp = pickle.load(open("./models/uci_imputer.sav", 'rb'))
    x = imp.transform([[value1, value2, value3, value4, value5, value6, value7]])

    transformer = pickle.load(open("./models/uci_transformer.sav", 'rb'))
    x = transformer.transform(x)

    scaler = pickle.load(open("./models/uci_scaler.sav", 'rb'))
    x = scaler.transform(x)

    model = pickle.load(open("./models/uci_model.sav", 'rb'))
    pred = model.predict(x)

    message_uci = ""
    if pred[0] <= 1:
        message_uci = translater.dic["hospitalization_1_day"][flag]
    elif pred[0] > 1 and pred[0] <= 2:
        message_uci = translater.dic["hospitalization_1_2_days"][flag]
    elif pred[0] > 2:
        message_uci = translater.dic["hospitalization_2_days"][flag]

    df = load_and_run.load_data(global_variables.outputs[2], ['PP nº procedimentos', 'ARISCAT duração cirurgia', 'ACS sépsis sistémica',
                                                              'ARISCAT infeção respiratória último mês', 'ARISCAT procedimento emergente',
                                                              'ARISCAT anemia pré-operativa', 'ACS insuficiência renal aguda'])

    X_graph = df.iloc[:, :-1]
    y_graph = df.iloc[:, -1].values
    X_graph = imp.transform(X_graph)
    X_graph = transformer.transform(X_graph)
    X_graph = scaler.transform(X_graph)
    preds = model.predict(X_graph)
    graph_preds = []

    for line in range(0, len(preds)):
        graph_preds.append([y_graph[line], preds[line], (preds[line] - y_graph[line])])
        # print(preds[line] - y_graph[line])

    df = pd.DataFrame(graph_preds,
                      columns=[translater.dic["uci_days"][flag], translater.dic["predicted_days"][flag], translater.dic["error_days"][flag]])

    fig_uci = px.scatter(df, x=translater.dic["predicted_days"][flag], y=translater.dic["uci_days"][flag])
    fig_uci_2 = px.scatter(df, x=translater.dic["predicted_days"][flag], y=translater.dic["error_days"][flag])

    return fig_uci, fig_uci_2, message_uci, pred


def predict_days_ipop(flag, value1, value10, value11, value2, value3, value4, value5, value6, value7, value8, value9):
    imp = pickle.load(open("./models/ipop_imputer.sav", 'rb'))
    x = imp.transform([[value1, value2, value3, value4, value5, value6, value7, value8, value9, value10, value11]])

    transformer = pickle.load(open("./models/ipop_transformer.sav", 'rb'))
    x = transformer.transform(x)

    scaler = pickle.load(open("./models/ipop_scaler.sav", 'rb'))
    x = scaler.transform(x)

    model = pickle.load(open("./models/ipop_model.sav", 'rb'))
    pred = model.predict(x)

    message_ipop = ""
    if pred[0] <= 7:
        message_ipop = translater.dic["hospitalization_7_days"][flag]
    elif pred[0] > 7 and pred[0] <= 10:
        message_ipop = translater.dic["hospitalization_7_10_days"][flag]
    elif pred[0] > 10 and pred[0] <= 20:
        message_ipop = translater.dic["hospitalization_10_20_days"][flag]
    elif pred[0] > 20:
        message_ipop = translater.dic["hospitalization_20_days"][flag]

    df = load_and_run.load_data(global_variables.outputs[6],
                                ['PP nº procedimentos', 'ACS sépsis sistémica', 'PP contaminação peritoneal', 'ACS estado funcional',
                                 'PP hemoglobina', 'PP leucócitos', 'ARISCAT duração cirurgia', 'PP sódio', 'PP respiratório', 'ACS dispneia',
                                 'PP ureia'])
    X_graph = df.iloc[:, :-1]
    y_graph = df.iloc[:, -1].values
    X_graph = imp.transform(X_graph)
    X_graph = transformer.transform(X_graph)
    X_graph = scaler.transform(X_graph)
    preds = model.predict(X_graph)
    graph_preds = []

    for line in range(0, len(preds)):
        graph_preds.append([y_graph[line], preds[line], y_graph[line] - preds[line]])

    df = pd.DataFrame(graph_preds,
                      columns=[translater.dic["ipop_days"][flag], translater.dic["predicted_days"][flag], translater.dic["error_days"][flag]])

    fig_ipop = px.scatter(df, x=translater.dic["predicted_days"][flag], y=translater.dic["ipop_days"][flag])
    fig_ipop_2 = px.scatter(df, x=translater.dic["predicted_days"][flag], y=translater.dic["error_days"][flag])

    return fig_ipop, fig_ipop_2, message_ipop, pred


def predict_death_1_year(flag, value1, value2, value3, value4, value5, value6, value7):
    imp = pickle.load(open("./models/death_1_year_imputer.sav", 'rb'))
    x = imp.transform([[value1, value2, value3, value4, value5, value6, value7]])
    transformer = pickle.load(open("./models/death_1_year_transformer.sav", 'rb'))
    x = transformer.transform(x)
    scaler = pickle.load(open("./models/death_1_year_scaler.sav", 'rb'))
    x = scaler.transform(x)
    model = pickle.load(open("./models/death_1_year_model.sav", 'rb'))
    pred = model.predict(x)
    probability = model.predict_proba(x)
    message_death_1_year = ""
    if pred[0] == 0:
        message_death_1_year = translater.dic["low_probability_death"][flag]
    elif pred[0] == 1:
        message_death_1_year = translater.dic["high_probability_death"][flag]
    df = load_and_run.load_data(global_variables.outputs[3],
                                ['ACS estado funcional', 'ASA', 'PP hemoglobina', 'PP estado da malignidade', 'ACS peso',
                                 'PP contaminação peritoneal', 'ACS sépsis sistémica'])
    X_graph = df.iloc[:, :-1]
    y_graph = df.iloc[:, -1]
    X_graph = imp.transform(X_graph)
    X_graph = transformer.transform(X_graph)
    X_graph = scaler.transform(X_graph)
    probs = model.predict_proba(X_graph)
    graph_probs = []
    for line in range(0, len(probs)):
        if y_graph.values[line] == 0:
            graph_probs.append([probs[line][0], translater.dic["no"][flag], translater.dic["training_data"][flag]])
        elif y_graph.values[line] == 1:
            graph_probs.append([probs[line][1], translater.dic["yes"][flag], translater.dic["training_data"][flag]])
    graph_probs.append([probability[0][0], translater.dic["no"][flag], translater.dic["current_patient"][flag]])
    graph_probs.append([probability[0][1], translater.dic["yes"][flag], translater.dic["current_patient"][flag]])
    df = pd.DataFrame(graph_probs, columns=[translater.dic["probability"][flag], translater.dic["death_1_year"][flag], 'Tipo'])
    fig_death_1_year = px.violin(df, y=translater.dic["probability"][flag], x=translater.dic["death_1_year"][flag], color='Tipo',
                                 violinmode='overlay', box=True, points="all", hover_data=df.columns)
    return fig_death_1_year, message_death_1_year


def predict_death_months(flag, value1, value2, value3, value4, value5, value6):
    imp = pickle.load(open("./models/death_months_imputer.sav", 'rb'))
    x = imp.transform([[value1, value2, value3, value4, value5, value6]])

    transformer = pickle.load(open("./models/death_months_transformer.sav", 'rb'))
    x = transformer.transform(x)
    scaler = pickle.load(open("./models/death_months_scaler.sav", 'rb'))

    x = scaler.transform(x)
    model = pickle.load(open("./models/death_months_model.sav", 'rb'))

    pred = model.predict(x)
    probability = model.predict_proba(x)

    message_death_months = ""
    if pred[0] == 1:
        message_death_months = translater.dic["death_30_days"][flag]
    elif pred[0] == 2:
        message_death_months = translater.dic["death_30_90_days"][flag]
    elif pred[0] == 3:
        message_death_months = translater.dic["death_90_days"][flag]
    df = load_and_run.load_data(global_variables.outputs[4],
                                ['ACS estado funcional', 'ASA', 'ACS sépsis sistémica', 'PP contaminação peritoneal', 'PP leucócitos',
                                 'PP estado da malignidade'])
    X_graph = df.iloc[:, :-1]
    y_graph = df.iloc[:, -1]
    X_graph = imp.transform(X_graph)
    X_graph = transformer.transform(X_graph)
    X_graph = scaler.transform(X_graph)
    probs = model.predict_proba(X_graph)
    graph_probs = []

    for line in range(0, len(probs)):
        if y_graph.values[line] == 1:
            graph_probs.append([probs[line][0], translater.dic["30_days"][flag], translater.dic["training_data"][flag]])
        elif y_graph.values[line] == 2:
            graph_probs.append([probs[line][1], translater.dic["30_90_days"][flag], translater.dic["training_data"][flag]])
        elif y_graph.values[line] == 3:
            graph_probs.append([probs[line][2], translater.dic["90_days"][flag], translater.dic["training_data"][flag]])

    graph_probs.append([probability[0][0], translater.dic["30_days"][flag], translater.dic["current_patient"][flag]])
    graph_probs.append([probability[0][1], translater.dic["30_90_days"][flag], translater.dic["current_patient"][flag]])
    graph_probs.append([probability[0][2], translater.dic["90_days"][flag], translater.dic["current_patient"][flag]])

    df = pd.DataFrame(graph_probs, columns=[translater.dic["probability"][flag], translater.dic["days_after_surgery"][flag], 'Tipo'])

    fig_death_months = px.violin(df, y=translater.dic["probability"][flag], x=translater.dic["days_after_surgery"][flag], color='Tipo',
                                 violinmode='overlay', box=True, points="all",
                                 category_orders={translater.dic["days_after_surgery"][flag]: [translater.dic["30_days"][flag],
                                                                                               translater.dic["30_90_days"][flag],
                                                                                               translater.dic["90_days"][flag]]},
                                 hover_data=df.columns)
    return fig_death_months, message_death_months


def predict_nas(flag, value1, value2, value3, value4):
    imp = pickle.load(open("./models/nas_imputer.sav", 'rb'))
    x = imp.transform([[value1, value2, value3, value4]])

    transformer = pickle.load(open("./models/nas_transformer.sav", 'rb'))
    x = transformer.transform(x)

    scaler = pickle.load(open("./models/nas_scaler.sav", 'rb'))
    x = scaler.transform(x)

    model = pickle.load(open("./models/nas_model.sav", 'rb'))
    pred = model.predict(x)

    message_nas = ""
    if pred[0] <= 60:
        message_nas = translater.dic["0_60_points"][flag]
    elif pred[0] > 60 and pred[0] <= 120:
        message_nas = translater.dic["60_120_points"][flag]
    elif pred[0] > 120:
        message_nas = translater.dic["120_points"][flag]

    df = load_and_run.load_data(global_variables.outputs[5],
                                ['ARISCAT duração cirurgia', 'PP nº procedimentos', 'ACS sépsis sistémica', 'PP leucócitos'])

    X_graph = df.iloc[:, :-1]
    y_graph = df.iloc[:, -1].values
    X_graph = imp.transform(X_graph)
    X_graph = transformer.transform(X_graph)
    X_graph = scaler.transform(X_graph)
    preds = model.predict(X_graph)
    graph_preds = []

    for line in range(0, len(preds)):
        graph_preds.append([y_graph[line], preds[line], y_graph[line] - preds[line]])

    df = pd.DataFrame(graph_preds,
                      columns=[translater.dic["nas"][flag], translater.dic["predicted_points"][flag], translater.dic["error_points"][flag]])

    fig_nas = px.scatter(df, x=translater.dic["predicted_points"][flag], y=translater.dic["nas"][flag])
    fig_nas_2 = px.scatter(df, x=translater.dic["predicted_points"][flag], y=translater.dic["error_points"][flag])

    return fig_nas, fig_nas_2, message_nas, pred


# Create a Dash layout
app.layout = page(flag)

if __name__ == '__main__':
    app.run_server(debug=True)
