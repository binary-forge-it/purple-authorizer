var Client = require('node-rest-client').Client;
var client = new Client();

if (process.argv.length != 6) {
  console.log('Usage: node ' + process.argv[1] + ' <host> <port> <input-file> <action>');
  process.exit(1);
}


var host = process.argv[2];
var port = process.argv[3];
var filename = process.argv[4];
var action = process.argv[5];

var fs = require('fs');
var inputEdges;
host = host + ":" + port

try {
	inputEdges = fs.readFileSync(filename).toString().split("\n");
	
} catch(e) {
	console.log('Error while processing input file, please verify it!');
	console.log("\nError message : " + e.message)
  process.exit(1);
}
 
var args = {
  data: { input: inputEdges },
  headers: { "Content-Type": "application/json" }
};

switch(action) {
	case "new":
		var endpoint = host + "/" + "purplegraph/api/rank"
		console.log("Performing POST at :" + endpoint)

		client.post(endpoint, args, function (data, response) {
			console.log("\n\t\t\t::::::::Response::::::::")
			console.log("Status code : "  + response.statusCode)
			console.log("ACK: " + response.headers.ack)
		});
		
		break;
	case "fraud":
		var endpoint = host + "/" + "purplegraph/api/fraud"
		console.log("Performing POST at :" + endpoint)

		client.post(endpoint, args, function (data, response) {
			console.log("\n\t\t\t::::::::Response::::::::")
			console.log("Status code : "  + response.statusCode)
			console.log("ACK: " + response.headers.ack)
		});

		break;
	case "reset":
		var endpoint = host + "/" + "purplegraph/api/rank/reset"
		console.log("Performing POST at :" + endpoint)

		client.post(endpoint, args, function (data, response) {
			console.log("\n\t\t\t::::::::Response::::::::")
			console.log("Status code : "  + response.statusCode)
			console.log("ACK: " + response.headers.ack)
		});

		break;
	case "show":
		var endpoint = host + "/" + "purplegraph/api/rank"
		console.log("Performing GET at :" + endpoint)

		client.get(host + "/" + "purplegraph/api/rank", args, function (data, response) {

			console.log("\n\t\t\t::::::::Response::::::::")
			console.log("Status code : "  + response.statusCode)
			console.log("ACK: " + response.headers.ack)
			
			console.log("\n\t\t\t::::::::Purple Graph Rank::::::::\n")

			for(var i = 0; i < data.length; i++) {
			  console.log("Label : " + data[i].label + " --- Score : " + data[i].score)
			}
		});
		break;
	default:
		console.log("Action '" + action + "' not found!");
}