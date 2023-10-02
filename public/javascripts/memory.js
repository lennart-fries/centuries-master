$('document').ready(function () {


        showOrHideCard = function (card) {

            if ($("#" + card.id + "-text").css("visibility") == "hidden") {
                $("#" + card.id + "-text").css('visibility', 'visible');

                //wait a few seconds
                setTimeout(function () {
                    var obj = {
                        "card": card.id
                    }

                    $.ajax({
                        url: "/evaluate",
                        data: JSON.stringify(obj),
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        type: 'POST',
                        success: function (data) {
                            let content = " ";
                            if (data.won) {
                                let c = document.getElementById("wrapper")
                                content += "<div id='memory-end' class='alert alert-info'><strong>You have finished the Memory successfully!</strong> <br> You earned three Coins! You can use your Coins to buy Cards at the Market!</div>";
                                c.innerHTML = content;
                                setTimeout(function () {
                                    window.location.replace("/map");
                                }, 2000)

                            }
                            else {
                                if (data.twoOpened) {
                                    if (data.rightAnswer) {
                                        //delete cards
                                        $("#" + data.openCardOne).css('visibility', 'hidden');
                                        $("#" + data.openCardTwo).css('visibility', 'hidden');
                                        $("#" + data.openCardOne + "-text").css('visibility', 'hidden');
                                        $("#" + data.openCardTwo + "-text").css('visibility', 'hidden');
                                    }
                                    else {
                                        //turn cards
                                        $("#" + data.openCardOne + "-text").css('visibility', 'hidden');
                                        $("#" + data.openCardTwo + "-text").css('visibility', 'hidden');
                                    }
                                }
                            }
                        }
                    });

                }, 350);
            }
            else {
                $("#" + card.id + "-text").css('visibility', 'hidden');
                $.ajax({
                    url: "/closeCard/" + card.id,
                    type: 'GET',
                });
            }

        }
    }
);

window.onload = function () {
    var token = $('input[name="csrfToken"]').attr('value');
    $.ajaxSetup({
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Csrf-Token', token);
        }
    });
};