#!/usr/bin/env bash
# Compila todo el proyecto y ejecuta los 5 principios SOLID.
# Uso:  ./run.sh            -> ejecuta los 5 principios
#       ./run.sh srp        -> ejecuta solo el principio indicado (srp|ocp|lsp|isp|dip)
set -e

cd "$(dirname "$0")"
mkdir -p target/classes

echo ">> Compilando..."
javac -encoding UTF-8 -d target/classes $(find src/main/java -name "*.java")
echo ">> Compilacion OK"
echo ""

PRINCIPIOS=("srp" "ocp" "lsp" "isp" "dip")
if [ -n "$1" ]; then
  PRINCIPIOS=("$1")
fi

for pkg in "${PRINCIPIOS[@]}"; do
  echo "================ ${pkg}.Main ================"
  java -cp target/classes "${pkg}.Main"
  echo ""
done
