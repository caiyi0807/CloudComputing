from flask import Flask, request, make_response

app = Flask(__name__)


@app.route("/")
def getClassification():
    module_1 = request.args.get('module_1', '')
    mark_1 = request.args.get('mark_1', 'null')
    module_2 = request.args.get('module_2', '')
    mark_2 = request.args.get('mark_2', 'null')
    module_3 = request.args.get('module_3', '')
    mark_3 = request.args.get('mark_3', 'null')
    module_4 = request.args.get('module_4', '')
    mark_4 = request.args.get('mark_4', 'null')
    module_5 = request.args.get('module_5', '')
    mark_5 = request.args.get('mark_5', 'null')

    marks = [mark_1, mark_2, mark_3, mark_4, mark_5]
    modules = [module_1, module_2, module_3, module_4, module_5]
    average = 0
    error = False

    j = 0
    while j < 5:
        if modules[j] == '':
            error = True
        j = j + 1

    i = 0
    j = 0
    while i < 5:
        if not marks[i].isdigit():
            error = True
            i = i + 1
            continue
        else:
            average += int(marks[i])
            j = j + 1
        i = i + 1

    if j == 0:
        average = 0
    else:
        average = average / j

    res = make_response({
        'error': error, 'marks': marks, 'modules': modules, 'average': average})
    res.status = '200'
    res.headers['Access-Control-Allow-Origin'] = "*"
    res.headers['Content-Type'] = 'application/json'

    return res
