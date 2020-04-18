// room dynamic list
const List = require("list.js");
const axios = require("axios").default;

axios.interceptors.request.use(request => {
    console.log('Starting Request', request)
    return request
});

axios.get('http://local.tom:8100/nese_rest_api_war/api/rooms', {
    /*headers: {
        'API_TOKEN': 'thomas',
    }*/
}).then(function (response) {
    console.log(response.data);
    console.log(response.status);
    console.log(response.statusText);
    console.log(response.headers);
    console.log(response.config);
}).catch(function (error) {
    console.log(error);
});

axios.get('http://local.tom:8100/nese_rest_api_war/api/rooms', {
    headers: {
        'API_TOKEN': 'thomas',
    }
}).then(function (response) {
    console.log(response.data);
    console.log(response.status);
    console.log(response.statusText);
    console.log(response.headers);
    console.log(response.config);
}).catch(function (error) {
    console.log(error);
});


(function () {
    let list = new List('room_table', { //underscores because of JSON
        valueNames: ['id', 'name']
    });

    let idField = $('#room_id_field'),
        nameField = $('#room_name_field'),
        addBtn = $('#room_add_btn'),
        editBtn = $('#room_edit_btn').hide();


    refreshCallbacks();

    addBtn.click(function () {
        list.add({
            id: Math.round(Math.random() * 999), //temporary id
            name: nameField.val(),
        });
        clearFields();
        refreshCallbacks();
    });

    editBtn.click(function () {
        let item = list.get('id', idField.val())[0];
        item.values({
            id: idField.val(),
            name: nameField.val(),
        });
        clearFields();
        editBtn.hide();
        addBtn.show();
    });

    function refreshCallbacks() {
        let editBtns = $('.room_edit_btns'),
            removeBtns = $('.room_remove_btns');

        removeBtns.click(function () {
            let itemId = $(this).closest('tr').find('.id').text();
            list.remove('id', itemId);
        });

        editBtns.click(function () {
            let itemId = $(this).closest('tr').find('.id').text();
            let itemValues = list.get('id', itemId)[0].values();
            idField.val(itemValues.id);
            nameField.val(itemValues.name);

            editBtn.show();
            addBtn.hide();
        });
    }

    function clearFields() {
        nameField.val('');
    }
})();

(function () {
    let list = new List('geraete_table', {
        valueNames: ['id', 'name', 'macaddress']
    });

    let idField = $('#geraete_id_field'),
        nameField = $('#geraete_name_field'),
        macaddressField = $('#geraete_macadresse_field'),

        addBtn = $('#geraete_add_btn'),
        editBtn = $('#geraete_edit_btn').hide();


    refreshCallbacks();

    addBtn.click(function () {
        list.add({
            id: Math.round(Math.random() * 999),
            name: nameField.val(),
            macaddress: macaddressField.val(),
        });
        clearFields();
        refreshCallbacks();
    });

    editBtn.click(function () {
        let item = list.get('id', idField.val())[0];
        item.values({
            id: idField.val(),
            name: nameField.val(),
            macaddress: macaddressField.val(),
        });
        clearFields();
        editBtn.hide();
        addBtn.show();
    });

    function refreshCallbacks() {
        let editBtns = $('.geraete_edit_btns'),
            removeBtns = $('.geraete_remove_btns');

        removeBtns.click(function () {
            let itemId = $(this).closest('tr').find('.id').text();
            list.remove('id', itemId);
        });

        editBtns.click(function () {
            let itemId = $(this).closest('tr').find('.id').text();
            let itemValues = list.get('id', itemId)[0].values();
            idField.val(itemValues.id);
            nameField.val(itemValues.name);
            macaddressField.val(itemValues.macaddress);

            editBtn.show();
            addBtn.hide();
        });
    }

    function clearFields() {
        nameField.val('');
        macaddressField.val('');
    }
})();

(function () {
    let list = new List('switch_table', {
        valueNames: ['id', 'name', 'ipaddress', 'communitystring']
    });

    let idField = $('#switch_id_field'),
        nameField = $('#switch_name_field'),
        ipaddressField = $('#switch_ipaddress_field'),
        communitystringField = $('#switch_communitystring_field'),

        addBtn = $('#switch_add_btn'),
        editBtn = $('#switch_edit_btn').hide();


    refreshCallbacks();

    addBtn.click(function () {
        list.add({
            id: Math.round(Math.random() * 999),
            name: nameField.val(),
            ipaddress: ipaddressField.val(),
            communitystring: communitystringField.val(),
        });
        clearFields();
        refreshCallbacks();
    });

    editBtn.click(function () {
        let item = list.get('id', idField.val())[0];
        item.values({
            id: idField.val(),
            name: nameField.val(),
            ipaddress: ipaddressField.val(),
            communitystring: communitystringField.val(),
        });
        clearFields();
        editBtn.hide();
        addBtn.show();
    });

    function refreshCallbacks() {
        let editBtns = $('.switch_edit_btns'),
            removeBtns = $('.switch_remove_btns');

        removeBtns.click(function () {
            let itemId = $(this).closest('tr').find('.id').text();
            list.remove('id', itemId);
        });

        editBtns.click(function () {
            let itemId = $(this).closest('tr').find('.id').text();
            let itemValues = list.get('id', itemId)[0].values();
            idField.val(itemValues.id);
            nameField.val(itemValues.name);
            ipaddressField.val(itemValues.ipaddress);
            communitystringField.val(itemValues.communitystring);

            editBtn.show();
            addBtn.hide();
        });
    }

    function clearFields() {
        nameField.val('');
        ipaddressField.val('');
        communitystringField.val('');
    }
})();

(function () {
    let list = new List('port_table', {
        valueNames: ['id', 'switchid', 'roomid', 'portnr']
    });

    let idField = $('#port_id_field'),
        switchidField = $('#connection_switch_id_field'),
        roomidField = $('#connection_room_id_field'),
        portnrField = $('#port_nr_field'),

        addBtn = $('#port_add_btn'),
        editBtn = $('#port_edit_btn').hide();

    refreshCallbacks();

    addBtn.click(function () {
        list.add({
            id: Math.round(Math.random() * 999),
            switchid: switchidField.val(),
            roomid: roomidField.val(),
            portnr: portnrField.val(),
        });
        clearFields();
        refreshCallbacks();
    });

    editBtn.click(function () {
        let item = list.get('id', idField.val())[0];
        item.values({
            id: idField.val(),
            switchid: switchidField.val(),
            roomid: roomidField.val(),
            portnr: portnrField.val(),
        });
        clearFields();
        editBtn.hide();
        addBtn.show();
    });

    function refreshCallbacks() {
        let editBtns = $('.port_edit_btns'),
            removeBtns = $('.port_remove_btns');

        removeBtns.click(function () {
            let itemId = $(this).closest('tr').find('.id').text();
            list.remove('id', itemId);
        });

        editBtns.click(function () {
            let itemId = $(this).closest('tr').find('.id').text();
            let itemValues = list.get('id', itemId)[0].values();
            idField.val(itemValues.id);
            switchidField.val(itemValues.switchid);
            roomidField.val(itemValues.roomid);
            portnrField.val(itemValues.portnr);

            editBtn.show();
            addBtn.hide
        });
    }

    function clearFields() {
        switchidField.val('');
        roomidField.val('');
        portnrField.val('');
    }
})();

(function () {
    let list = new List('list_table', {
        valueNames: ['id', 'name', 'macadress']
    });

})();
