from flask import Flask, request, Response, make_response

import average
import json

app = Flask(__name__)


@app.route("/")
def getAverage():
    module_1 = request.args.get('module_1', '')
    mark_1 = request.args.get('mark_1', '')
    module_2 = request.args.get('module_2', '')
    mark_2 = request.args.get('mark_2', '')
    module_3 = request.args.get('module_3', '')
    mark_3 = request.args.get('mark_3', '')
    module_4 = request.args.get('module_4', '')
    mark_4 = request.args.get('mark_4', '')
    module_5 = request.args.get('module_5', '')
    mark_5 = request.args.get('mark_5', '')
    marks = [mark_1, mark_2, mark_3, mark_4, mark_5]
    modules = [module_1, module_2, module_3, module_4, module_5]
    errorInformation = []
    error = False
    j = 0
    while j < len(modules):
        modules[j] = modules[j].strip()
        if modules[j] == "":
            error = True
            j = j + 1
            errorInformation.append("Please input the name of module_" + str(j) + ".")
            continue
        j = j + 1
    i = 0
    sum = 0
    num = 0
    while i < len(marks):
        marks[i] = marks[i].strip()
        if marks[i] == '':
            error = True
            i=i+1
            errorInformation.append("Please input value for mark_" + str(i) + ".")
            continue
        if not marks[i].isdigit():
            error = True
            marks[i] = ""
            i=i+1
            errorInformation.append("Please input integer for mark_" + str(i) + " and it should be greater than 0.")
            continue
        if int(marks[i]) > 100:
            error = True
            marks[i] = ""
            i=i+1
            errorInformation.append("Mark_" + str(i) + " should be less than 100.")
            continue
        else:
            sum += int(marks[i])
            num = num + 1
        i = i + 1
    averagemark = average.averageMark(sum, num)
    r = {
        "error": error,
        "marks": marks,
        "modules": modules,
        "average": averagemark,
        "errrorInformation": errorInformation
    }
    reply = json.dumps(r)
    response = Response(response=reply, status=200, mimetype='application/json')
    response.headers["Access-Control-Allow-Origin"] = "*"
    response.headers["Content-Type"] = "application/json"
    # return response

    with open('text01.txt', 'a') as file_read:
        file_read.write(reply)
        file_read.write(',')
    return response


@app.route("/history")
def Read():
    with open('text01.txt', 'r') as file_read:
        content = file_read.read()
    # res = make_response(content.replace('\n', ',').strip(','))
    # res.headers['Access-Control-Allow-Origin'] = "*"
    # res.headers['Content-Type'] = 'application/json'
    response=Response(response=content.strip(','),status=200,mimetype='application/json')
    return response


@app.route("/clear")
def Clear():
    with open('text01.txt', 'r+') as file_read:
        file_read.truncate(0)
    res = make_response()
    res.headers['Access-Control-Allow-Origin'] = "*"
    res.headers['Content-Type'] = 'application/json'
    return res

if __name__ == "__main__":
    app.run(debug=True, host='0.0.0.0')
