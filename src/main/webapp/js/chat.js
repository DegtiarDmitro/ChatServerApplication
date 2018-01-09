window.onload = function() {

     var userName = $('#username').val();
     //var pass = $('#pass').val();
    //
    // const ws = new WebSocket("ws://h2744859.stratoserver.net:8080/chat-server-application/chat/" + userName);
    const ws = new WebSocket("ws://192.168.43.227:8080/chat");
    // //const ws = new WebSocket("ws://localhost:8080/chat/" + userName);
    // ws.onmessage = function (event) {
    //     var message = JSON.parse(event.data);
    //     //alert(message);
    //     if(message['message']){
    //         addMessageToChat(message['message'].from, message['message'].content);
    //     }
    //
    // };

    //validateUser();

    $('#submitmsg').click(function (e) {
        e.preventDefault();
        var input = $('#usermsg');
        //var message = input.val();
        //var file = document.getElementById('filename').files[0];
        // if(message !== ""){
        //     sendTextMessage(message)
        // }else if(file !== null){
        //     sendFile(file)
        // }

        input.val("");
        return false;
    });

    // function validateUser() {
    //     ws.send(JSON.stringify({
    //         'authentication ':{
    //             'username': userName,
    //             'password': pass,
    //             'role': 'BUYER'
    //         }
    //     }));
    // }


    // function sendTextMessage(message) {
    //     ws.send(JSON.stringify({
    //         'message':{
    //             'from': userName,
    //             'to': 'manager',
    //             'content': message
    //         }
    //     }));
    //     addMessageToChat(userName, message);
    // }
    //
    // function sendFile(file) {
    //     ws.send('filename:'+file.name);
    //     var reader = new FileReader();
    //     var rawData = new ArrayBuffer();
    //     //alert(file.name);
    //
    //     reader.loadend = function() {};
    //     reader.onload = function(e) {
    //         rawData = e.target.result;
    //         ws.send(rawData);
    //         //alert("the File has been transferred.")
    //         ws.send('end');
    //     };
    //
    //     reader.readAsArrayBuffer(file);
    //
    // }



    // function addMessageToChat(userName, content) {
    //     var row = $("<tr>");
    //     row.append($("<td><b>"+userName+": </b></td>"));
    //     row.append($("<td><span>"+content+"</span></td>"));
    //     $("#messages").append(row);
    // }
};