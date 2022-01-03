#!/bin/sh

export DISPLAY=localhost:0
java --module-path "../../javaFX/MacOS/lib" --add-modules javafx.controls,javafx.base,javafx.fxml,javafx.graphics,javafx.media,javafx.web --add-opens=javafx.graphics/javafx.scene=ALL-UNNAMED --add-exports javafx.base/com.sun.javafx.event=ALL-UNNAMED -jar ../jar/lanceurJoueur.jar
