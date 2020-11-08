package main

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"

	"github.com/gorilla/mux"
)

type Page struct {
	Title string
	Body  []byte
}

type Run struct {
	index int
	name  string
	steps string
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
	s := "GET"
	fmt.Fprintf(w, s)
}

// https://www.alexedwards.net/blog/how-to-properly-parse-a-json-request-body

func PostRunHandler(w http.ResponseWriter, r *http.Request) {
	body, err := ioutil.ReadAll(r.Body)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	str := string(body)
	fmt.Println(str)

	decoder := json.NewDecoder(r.Body)
	var run Run
	err2 := decoder.Decode(&run)

	if err2 != nil {
		http.Error(w, err2.Error(), http.StatusBadRequest)
		return
	}
	fmt.Println(run.index)
	fmt.Println(run.name)
	fmt.Println(run.steps)

	s := "POST"
	fmt.Fprintf(w, s)
}

func main() {
	r := mux.NewRouter()

	r.HandleFunc("/run", PostRunHandler).Methods("POST")
	r.HandleFunc("/run/{index}", GetRunHandler).Methods("GET")
	r.HandleFunc("/count", GetCountHandler).Methods("GET")
	r.HandleFunc("/error", GetErrorHandler).Methods("GET")
	http.ListenAndServe(":8080", r)
}
