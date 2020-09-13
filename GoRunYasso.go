package main

import (
	"fmt"
	"io/ioutil"
	"net/http"

	"github.com/gorilla/mux"
)

type Page struct {
	Title string
	Body  []byte
}

func loadRun(title string) (*Page, error) {
	filename := title + ".txt"
	body, err := ioutil.ReadFile(filename)
	if err != nil {
		return nil, err
	}
	return &Page{Title: title, Body: body}, nil
}

func GetRunHandler(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	index := vars["index"]

	title := "run" + index
	p, err := loadRun(title)

	if err != nil {
		http.Redirect(w, r, "/error", http.StatusFound)
		return
	}
	s := string(p.Body)
	fmt.Fprintf(w, s)
}

func GetCountHandler(w http.ResponseWriter, r *http.Request) {
	s := "[{\"count\":8}]"
	fmt.Fprintf(w, s)
}

func GetErrorHandler(w http.ResponseWriter, r *http.Request) {
	s := "Error"
	fmt.Fprintf(w, s)
}

func main() {
	r := mux.NewRouter()

	r.HandleFunc("/run/{index}", GetRunHandler).Methods("GET")
	r.HandleFunc("/count", GetCountHandler).Methods("GET")
	r.HandleFunc("/error", GetErrorHandler).Methods("GET")
	http.ListenAndServe(":8080", r)
}
