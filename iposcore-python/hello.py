# from flask import Flask, jsonify, render_template
# import random

# app = Flask(__name__)

# movies = {1: 'Toy story', 2: 'The Raid', 3: 'Hero',
#             4: 'Ip Man', 5: 'Kung Fu Panda'}


# @app.route('/movies')
# def get_movies():

#     return movies


# @app.route('/rmovie')
# def random_movie():

#     movie = random.choice(list(movies.items()))

#     return jsonify(movie)

from flask import Flask, request

app = Flask(__name__)


@app.route('/')
def index():

    return 'Home page'


@app.route('/greet', methods=['GET'])
def greet():

    name = request.args.get('name', 'Guest')
    msg = f'Hello {name}'

    return msg, 200, {'Content-Type': 'text/plain; charset=utf-8'}