$("#file-input-1").fileinput({
    uploadUrl : "http://localhost/file-upload-single/1", // server upload
    // action
    uploadAsync : true,
    maxFileCount : 5
});

function upload() {
    $('#file-input-1').fileinput('refresh', {
        uploadUrl : '/upload',
    });
    $('#file-input-1').fileinput('upload');
}