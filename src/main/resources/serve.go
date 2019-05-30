package main

import (
	"net/http"
)

var port string
var static string

func init() {
	port = "443"
	static = "static"
}

func main() {
	println(port)
	http.Handle("/", http.FileServer(http.Dir(static)))
	http.ListenAndServeTLS(":"+port, "server.crt", "key.pem", nil)
}
