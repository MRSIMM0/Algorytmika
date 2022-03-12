from email import header
from dash import Dash, dcc, html,dash_table
from matplotlib import font_manager
import plotly.express as px
import plotly.graph_objects as go
from plotly.graph_objects import Layout
import pandas as pd
def visualization(data,algorithms):
    app = Dash(__name__)
    
  
    df = {"setSize":[]}
  
    for x in data["tests"]:
        df["setSize"].append( data["tests"][x]['setSize'])
        for y in range(len(algorithms)):
            try:
                df[algorithms[y].name].append(data["tests"][x]["algorithms"][y][1])
            except:
                df[algorithms[y].name] = [data["tests"][x]["algorithms"][y][1]]
  
    df = pd.DataFrame(df)

    colors = {
        'background': '#E6E8E6',
        'text': '#F15025'
    }

    vals = [list(df["setSize"])]
    [vals.append(list(df[x.name])) for x in algorithms]
    fig = px.line(df, x="setSize", y=[x.name for x in algorithms],markers=True, labels=dict(value="Time (s)", setSize="Set Size",color="Place",variable="Algorithms"))

    fig2= go.Figure(go.Table(header=dict(values=list(df.columns)),
     cells = dict(values = vals)
    ))
   

    fig.update_layout(
        plot_bgcolor="#E6E8E6",
        paper_bgcolor="white",
        font_color=colors['text'],

        
    )
    fig2.update_layout(  
        plot_bgcolor="#E6E8E6",
        paper_bgcolor="white",
        font_color=colors['text'])

    app.layout = html.Div(style={'backgroundColor': colors['background']}, children=[
      

        html.Div(children='Wizaulizacja porównania algorytmów porównania', style={
            'textAlign': 'center',
            'color': colors['text']
        }),

       

         html.Div(children=dcc.Graph(
            id='example-graph-1',
            figure=fig
        )),
         html.Div(children=dcc.Graph(
            id='example-graph-2',
            figure=fig2
        )),
    ])
    
   


    app.run_server(debug=True,port=8082)