const List = require("list.js");
const axios = require("axios").default;

axios.interceptors.request.use(request => {
    console.log('Starting Request', request);
    return request;
});

axios.interceptors.response.use(response => {
    console.log('Received Response', response);
    return response;
}, response => {
    console.log('Received Rejection', response);
    return Promise.reject(response);
});

axios.defaults.baseURL = 'http://' + process.env.MIX_API_HOST + '/nese_rest_api_war/api';
axios.defaults.headers.common['API_TOKEN'] = process.env.MIX_API_TOKEN;

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
        axios.post(`/rooms?name=${nameField.val()}`, {})
            .then(function (response) {
                list.add({
                    id: response.data.id_room,
                    name: response.data.name
                });
            }).catch(function (error) {
            console.log(error);
        }).then(function () {
            clearFields();
            refreshCallbacks();
        });
    });

    editBtn.click(function () {
        let item = list.get('id', idField.val())[0];
        let id = idField.val();
        let name = nameField.val();

        axios.put(`/rooms/?id=${id}&name=${name}`, {})
            .then(function () {
                item.values({
                    id: id,
                    name: name,
                });
            }).catch(function (error) {
            console.log(error);
        }).then(function () {
            clearFields();
            editBtn.hide();
            addBtn.show();
        });
    });

    function refreshCallbacks() {
        let editBtns = $('.room_edit_btns'),
            removeBtns = $('.room_remove_btns');

        removeBtns.click(function () {
            let itemId = $(this).closest('tr').find('.id').text();

            axios.delete(`/rooms/${itemId}`)
                .then(function () {
                    list.remove('id', itemId);
                }).catch(function (error) {
                if (error.response) {
                    let err = error.response;
                    alert(err.data.message);
                }
                console.log(error);
            });
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
        axios.post(`/devices?name=${nameField.val()}&mac=${macaddressField.val()}`, {})
            .then(function (response) {
                list.add({
                    id: response.data.id_device,
                    name: response.data.name,
                    macaddress: response.data.mac,
                });
            }).catch(function (error) {
            if (error.response) {
                let err = error.response;
                alert(err.data.message);
            }
            console.log(error);
        }).then(function () {
            clearFields();
            refreshCallbacks();
        });
    });

    editBtn.click(function () {
        let item = list.get('id', idField.val())[0];
        let id = idField.val();
        let name = nameField.val();
        let macaddress = macaddressField.val();

        axios.put(`/devices/?id=${id}&name=${name}&mac=${macaddress}`, {})
            .then(function () {
                item.values({
                    id: id,
                    name: name,
                    macaddress: macaddress,
                });
            }).catch(function (error) {
            if (error.response) {
                let err = error.response;
                alert(err.data.message);
            }
            console.log(error);
        }).then(function () {
            clearFields();
            editBtn.hide();
            addBtn.show();
        });
    });

    function refreshCallbacks() {
        let editBtns = $('.geraete_edit_btns'),
            removeBtns = $('.geraete_remove_btns');

        removeBtns.click(function () {
            let itemId = $(this).closest('tr').find('.id').text();
            axios.delete(`/devices/${itemId}`)
                .then(function () {
                    list.remove('id', itemId);
                }).catch(function (error) {
                if (error.response) {
                    let err = error.response;
                    alert(err.data.message);
                }
                console.log(error);
            });
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
        axios.post(`/switches?name=${nameField.val()}&ip=${ipaddressField.val()}&community_string=${communitystringField.val()}`, {})
            .then(function (response) {
                list.add({
                    id: response.data.id_switch,
                    name: response.data.name,
                    ipaddress: response.data.ip,
                    communitystring: response.data.community_string
                });
            }).catch(function (error) {
            if (error.response) {
                let err = error.response;
                alert(err.data.message);
            }
            console.log(error);
        }).then(function () {
            clearFields();
            refreshCallbacks();
        });
    });

    editBtn.click(function () {
        let item = list.get('id', idField.val())[0];
        let id = idField.val();
        let name = nameField.val();
        let ipaddress = ipaddressField.val();
        let communitystring = communitystringField.val();

        axios.put(`/switches?id=${id}&name=${name}&ip=${ipaddress}&community_string=${communitystring}`, {})
            .then(function () {
                item.values({
                    id: id,
                    name: name,
                    ipaddress: ipaddress,
                    communitystring: communitystring,
                });
            }).catch(function (error) {
            if (error.response) {
                let err = error.response;
                alert(err.data.message);
            }
            console.log(error);
        }).then(function () {
            clearFields();
            editBtn.hide();
            addBtn.show();
        });
    });

    function refreshCallbacks() {
        let editBtns = $('.switch_edit_btns'),
            removeBtns = $('.switch_remove_btns');

        removeBtns.click(function () {
            let itemId = $(this).closest('tr').find('.id').text();

            axios.delete(`/switches/${itemId}`)
                .then(function () {
                    list.remove(`id`, itemId);
                }).catch(function (error) {
                if (error.response) {
                    let err = error.response;
                    alert(err.data.message);
                }
                console.log(error);
            });
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
        axios.post(`/portconnections?switch_id=${switchidField.val()}&room_id=${roomidField.val()}&port=${portnrField.val()}`, {})
            .then(function (response) {
                list.add({
                    id: response.data.id_port_connection,
                    switchid: response.data.switch_id,
                    roomid: response.data.room_id,
                    portnr: response.data.port
                });
            }).catch(function (error) {
            if (error.response) {
                let err = error.response;
                alert(err.data.message);
            }
            console.log(error);
        }).then(function () {
            clearFields();
            refreshCallbacks()
        });
    });

    editBtn.click(function () {
        let item = list.get('id', idField.val())[0];
        let id = idField.val();
        let switchid = switchidField.val();
        let roomid = roomidField.val();
        let portnr = portnrField.val();

        axios.put(`/portconnections?id=${id}&switch_id=${switchid}&room_id=${roomid}&port=${portnr}`, {})
            .then(function () {
                item.values({
                    id: id,
                    switchid: switchid,
                    roomid: roomid,
                    portnr: portnr,
                });
            }).catch(function (error) {
            if (error.response) {
                let err = error.response;
                alert(err.data.message);
            }
            console.log(error);
        }).then(function () {
            clearFields();
            editBtn.hide();
            addBtn.show();
        });
    });

    function refreshCallbacks() {
        let editBtns = $('.port_edit_btns'),
            removeBtns = $('.port_remove_btns');

        removeBtns.click(function () {
            let itemId = $(this).closest('tr').find('.id').text();

            axios.delete(`/portconnections/${itemId}`)
                .then(function () {
                    list.remove('id', itemId);
                }).catch(function (error) {
                if (error.response) {
                    let err = error.response;
                    alert(err.data.message);
                }
                console.log(error);
            });
        });

        editBtns.click(function () {
            let itemId = $(this).closest('tr').find('.id').text();
            let itemValues = list.get('id', itemId)[0].values();
            idField.val(itemValues.id);
            switchidField.val(itemValues.switchid);
            roomidField.val(itemValues.roomid);
            portnrField.val(itemValues.portnr);

            editBtn.show();
            addBtn.hide()
        });
    }

    function clearFields() {
        switchidField.val('');
        roomidField.val('');
        portnrField.val('');
    }
})();

(function () {
    new List('list_table', {
        valueNames: ['id', 'name', 'macadress']
    });
})();
