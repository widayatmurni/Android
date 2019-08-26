<?php
$target_dir = "uploads/";
$tmp_file_name = basename($_FILES["upload_file_name"]["name"]);
$imageFileType = strtolower(pathinfo($tmp_file_name)["extension"]);
$target_file_name = $target_dir . "upload-" . time() . pathinfo($tmp_file_name)["filename"] . "." .$imageFileType;

if (move_uploaded_file($_FILES["upload_file_name"]["tmp_name"], $target_file_name)) {
        $respons["status"] = 1;
        $respons["message"] = "File berhasil diupload";
        
} else {
        $respons["status"] = 0;
        $respons["message"] = "Error Uploading";
}


echo json_encode($respons);


