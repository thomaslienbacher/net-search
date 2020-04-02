// room dynamic list
const List = require("list.js");

(function () {
    let list = new List('room_table', {
        valueNames: ['id', 'name']
    });

    let idField = $('#room_id_field'),
        nameField = $('#room_name_field'),
        addBtn = $('#room_add_btn'),
        editBtn = $('#room_edit_btn').hide();

    refreshCallbacks();

    addBtn.click(function () {
        list.add({
            id: Math.round(Math.random() * 999),
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
