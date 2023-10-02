function levelchange(level) {
    window.location.href = "/level/" + level;
}

window.onload = function () {
    var map = document.getElementById("map");
    var svgDoc = map.contentDocument;

    var markers = svgDoc.querySelectorAll('.marker');
    for (let i = 0, element; element = markers[i]; i++) {
        //rescale markers
        element.style.height = '12';
        element.style.width = '8';
        element.style.opacity = '0.93';

        //add links
        element.addEventListener('click', function () {
            window.location = "/" + this.id;
        }, false)

        //add mouseEvents
        element.addEventListener('mouseover', function () {
            element.style.cursor = 'pointer';
            element.style.opacity = '1.0';
        }, false)

        element.addEventListener('mouseout', function () {
            element.style.opacity = '0.93';
        }, false)
    }

    var token = $('input[name="csrfToken"]').attr('value');
    $.ajaxSetup({
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Csrf-Token', token);
        }
    });


};



