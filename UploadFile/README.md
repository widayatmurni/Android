Upload file menggunkan okhttp

untuk menggunkan okhttp pada project android, ada beberapa langkah yang perlu dilakukan :

1. 	tambah kan
	
	repositories {
        	maven {
            		url 'http://dl.bintray.com/lukaville/maven'
        	}
    	}

2.	Tambahkan dependencies

	implementation 'com.squareup.okhttp3:okhttp:4.1.0'
    	implementation 'com.nbsp:library:1.8'

3. 	db/upload.php sebagai handler pada sisi server