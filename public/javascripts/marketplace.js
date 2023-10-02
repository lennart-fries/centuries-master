function buyCard(card) {
    $.post({
        url: "transaction",
        data: JSON.stringify({"Card": card}),
        contentType: 'application/json; charset=UTF-8',
        success: function (data) {
            let e = document.getElementById("cards");
            let content = " "
            if (data.failed) {
                content += "<div class='alert alert-danger status'><strong> The " + card + " could not be bought!</strong> <br> Make sure you have enough Coins to buy a " + card + "</div>";
                e.innerHTML = content;
            } else {
                content += "<div class='alert alert-success status'><strong> A " + card + " was bought!</strong> <br> The Card has been added to your Inventory</div>";
                e.innerHTML = content;
            }
            setTimeout(function () {
                location.reload();
            }, 2000)


        },
    });
}