$(document).ready(function () {

    // Switching menu
    // TODO оптимизировать меню
    setMenuActiveElement('#converter');

    $('#myForm').validator().on('submit', function (e) {
        if (e.isDefaultPrevented()) {
            // handle the invalid form...
            addError('#myError', '#errorMessage', 'Ошибка валидации формы');
        } else {
            // everything looks good!
            e.preventDefault();

            // alert('validator good');

            var xmlString = $("#areaFrom").val();
            // Документ
            var number = $("#number").val();
            var date = $("#date").val();
            var shippingDate = $("#shippingDate").val();
            var base = $("#base").val();
            var note = $("#note").val();
            // Отправитель
            var shipperFsrarId = $("#shipperFsrarId").val();
            var shipperInn = $("#shipperInn").val();
            var shipperKpp = $("#shipperKpp").val();
            var shipperName = $("#shipperName").val();
            var shipperAddress = {
                country: $("#shipperAddressCountry").val(),
                description: $("#shipperAddressDescription").val(),
                index: $("#shipperAddressIndex").val(),
                regionCode: $("#shipperAddressRegionCode").val(),
                area: $("#shipperAddressArea").val(),
                city: $("#shipperAddressCity").val(),
                place: $("#shipperAddressPlace").val(),
                street: $("#shipperAddressStreet").val(),
                house: $("#shipperAddressHouse").val(),
                building: $("#shipperAddressBuilding").val(),
                liter: $("#shipperAddressLiter").val()
            };
            // Получатель
            var consigneeFsrarId = $("#consigneeFsrarId").val();
            var consigneeInn = $("#consigneeInn").val();
            var consigneeKpp = $("#consigneeKpp").val();
            var consigneeName = $("#consigneeName").val();
            var consigneeAddress = {
                country: $("#consigneeAddressCountry").val(),
                description: $("#consigneeAddressDescription").val(),
                index: $("#consigneeAddressIndex").val(),
                regionCode: $("#consigneeAddressRegionCode").val(),
                area: $("#consigneeAddressArea").val(),
                city: $("#consigneeAddressCity").val(),
                place: $("#consigneeAddressPlace").val(),
                street: $("#consigneeAddressStreet").val(),
                house: $("#consigneeAddressHouse").val(),
                building: $("#consigneeAddressBuilding").val(),
                liter: $("#consigneeAddressLiter").val()
            };

            var sendData = {
                number: number,
                date: date,
                shippingDate: shippingDate,
                base: base,
                note: note,
                shipperFsrarId: shipperFsrarId,
                shipperInn: shipperInn,
                shipperKpp: shipperKpp,
                shipperName: shipperName,
                shipperAddress: shipperAddress,
                consigneeFsrarId: consigneeFsrarId,
                consigneeInn: consigneeInn,
                consigneeKpp: consigneeKpp,
                consigneeName: consigneeName,
                consigneeAddress: consigneeAddress,
                xmlString: xmlString
            };

            $.post("/convert", sendData)
                .done(function (data) {
                    // alert("done " + data);
                    $("#areaTo").val(data);
                })
                .fail(function () {
                    // alert("fail");
                    addError('#myError', '#errorMessage', 'Ошибка отправки сообщения методом POST.');
                })
                .always(function (data) {
                    // alert("finished " + data);
                });
        }
        return this;
    });
});