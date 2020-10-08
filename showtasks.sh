#!/usr/bin/env bash

openBrowser() {
  open -a Safari http://localhost:8080/crud/v1/task/getTasks
}

fail() {
  echo "error"
}

if ./runcrud.sh; then
   openBrowser
else
   fail
fi