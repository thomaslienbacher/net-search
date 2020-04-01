// room dynamic list

var roomList = new List('room_table', {
    valueNames: ['id', 'name']
});

(function () {
    let idField = $('#room_id_field'),
        nameField = $('#room_name_field'),
        addBtn = $('#room_add_btn'),
        editBtn = $('#room_edit_btn').hide(),
        editBtns = $('.room_edit_btns'),
        removeBtns = $('.room_remove_btns');

    refreshCallbacks();

    addBtn.click(function () {
        roomList.add({
            id: -1,
            name: nameField.val(),
        });
        clearFields();
        refreshCallbacks();
    });

    editBtn.click(function () {
        let item = roomList.get('id', idField.val())[0];
        item.values({
            id: idField.val(),
            name: nameField.val(),
        });
        clearFields();
        editBtn.hide();
        addBtn.show();
    });

    function refreshCallbacks() {
        removeBtns.click(function () {
            let itemId = $(this).closest('tr').find('.id').text();
            roomList.remove('id', itemId);
        });

        editBtns.click(function () {
            let itemId = $(this).closest('tr').find('.id').text();
            let itemValues = roomList.get('id', itemId)[0].values();
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
