var UIGeneral = function () {

    var handlePulsate = function () {
        if (!jQuery().pulsate) {
            return;
        }

        if (Metronic.isIE8() == true) {
            return;
        }

        if (jQuery().pulsate) {
            jQuery('.pulsate-regular').pulsate({
                color: "#bf1c56",
                repeat: false,
            });
        }
    }

    return {
        init: function () {
            handlePulsate();
        }

    };

}();