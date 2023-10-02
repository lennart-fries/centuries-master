jQuery.fn.extend({
    disable: function(state) {
        return this.each(function() {
            this.disabled = state;
        });
    }
});

function setParameters(answerId) {
    $('input[type="button"], button[name="answer-submit"]').disable(false);
    selectAnswer(answerId);
    getQuiz();
    getQuestion();

}

function chooseCard(cardId) {
    $('input[type="button"], button[name="card-submit"]').disable(false);
    selectCard(cardId);
    getQuiz();
    getQuestion();
}

function selectCard(cardId) {
    card = cardId
}

function selectAnswer(id) {
    answer = id;
}

function getQuiz() {
    quiz = document.getElementById("quizid").innerHTML;
}

function getQuestion() {
    question = document.getElementById("questionid").innerHTML;
}



function submitAnswer() {
            $.post({
            url: "question",
            data: JSON.stringify({"quiz": quiz, "question": question, "answer": answer}),
            contentType: 'application/json; charset=UTF-8',
            success: function (data) {
                let e = document.getElementById("question");
                let content = " ";
                if (data.finished) {
                    let c = document.getElementById("con");
                    content += "<div id='quiz-end' class='alert alert-info'><strong>You have finished the Quiz with " + data.percentage + "% correct Answers!</strong> <br> If this was your first time finishing this Quiz you will now be able to see your Progress with it in the Quiz Selection! <br> If you were worse than before, dont worry, your all time best counts!</div>"
                    c.innerHTML = content;
                    setTimeout(function () {
                        window.location.replace("/quiz");
                    },2000)
                }
                let num = 0;
                for (q in data.questions) {
                    if (num === 0) {
                        content += "<h3 id='content'>" + data.questions[q] + "</h3>";
                        num += 1;
                    } else if (num > 0 && num < data.questions.length - 1) {
                        if (num === 2) {
                            content += "<button type='button' class='btn btn-lg btn-default' onclick='setParameters(" + num + ")'><p class='answer'>" + data.questions[q] + "</p></button><br>";
                            num += 1;
                        } else {
                            content += "<button type='button' class='btn btn-lg btn-default' onclick='setParameters(" + num + ")'><p class='answer'>" + data.questions[q] + "</p></button>";
                            num += 1;
                        }
                    } else {
                        content += "<p hidden id='questionid'>" + data.questions[q] + "</p><br>"
                        num += 1;
                    }
                }
                e.innerHTML = content;
                $('input[type="button"], button[name="answer-submit"], button[name="card-submit"]').disable(true)
            },

        });
}

function useSelectedCard() {
    $.post({
        url: "card",
        data: JSON.stringify({"quiz": quiz, "question": question, "card": card}),
        contentType: 'application/json; charset=UTF-8',
        success: function (data) {
            let e = document.getElementById("question");
            let content = " ";
            let num = 0;
            if (data.none) {
                content +=  "<div class='alert alert-danger status'><strong> Could not use a " + card + "</strong> <br> Make sure you own at least one copy of the Card you want to use and try again!</div>";
                e.innerHTML = content;
                setTimeout(function () {
                    content = " ";
                    let num = 0;
                    for (q in data.questions) {
                        if (num === 0) {
                            content += "<h3 id='content'>" + data.questions[q] + "</h3>";
                            num += 1;
                        } else if (num > 0 && num < data.questions.length - 1) {
                            if (num === 2) {
                                content += "<button type='button' class='btn btn-lg btn-default' onclick='setParameters(" + num + ")'><p class='answer'>" + data.questions[q] + "</p></button><br>";
                                num += 1;
                            } else {
                                content += "<button type='button' class='btn btn-lg btn-default' onclick='setParameters(" + num + ")'><p class='answer'>" + data.questions[q] + "</p></button>";
                                num += 1;
                            }
                        } else {
                            content += "<p hidden id='questionid'>" + data.questions[q] + "</p><br>"
                            num += 1;
                        }
                    }
                    e.innerHTML = content;
                    $('input[type="button"], button[name="answer-submit"], button[name="card-submit"]').disable(true);
                },2000)
            }

            for (q in data.questions) {
                if (num === 0) {
                    content += "<h3 id='content'>" + data.questions[q] + "</h3>";
                    num += 1;
                } else if (num > 0 && num < data.questions.length-1) {
                    if (data.striked.includes(num)) {
                        if(num == 2) {
                            content += "<button type='button' class='btn btn-lg btn-default disabled'><p class='answer'>"+ data.questions[q] + "</p></button><br>";
                            num += 1;
                        } else {
                            content += "<button type='button' class='btn btn-lg btn-default disabled'><p class='answer'>" + data.questions[q] + "</p></button>";
                            num += 1;
                        }
                    } else {
                        if(num == 2) {
                            content += "<button type='button' class='btn btn-lg btn-default' onclick='setParameters("+ num +")'><p class='answer'>"+ data.questions[q]+"</p></button><br>";
                            num += 1;
                        } else {
                            content += "<button type='button' class='btn btn-lg btn-default' onclick='setParameters("+ num +")'><p class='answer'>"+ data.questions[q]+"</p></button>";
                            num += 1;
                        }
                    }
                } else {
                    content += "<p hidden id='questionid'>" + data.questions[q] + "</p><br>"
                    num += 1;
                }
            }
            e.innerHTML = content;

        }
    });
}
