#!/usr/bin/env cljog
(deps '[[io.axrs/shuck "0.0.0-SNAPSHOT"]
        [io.axrs/prospect "0.0.0-SNAPSHOT"]
        [clj-jgit "1.0.0"]
        [leiningen "2.9.1" :exclusions [org.slf4j/slf4j-nop]]])

(require
  '[clj-jgit.porcelain :refer [git-status load-repo]]
  '[io.axrs.prospect.core :as prospect]
  '[io.axrs.shuck.core :as shuck]
  '[leiningen.core.main :as lein]
  '[leiningen.core.project :as project])

(def ^:private repo (delay (load-repo *cwd*)))

(defn- git-dirty? []
  (every? (comp some? seq) (vals (git-status @repo))))

(defn- project []
  (-> "project.clj"
      (project/read [:default])))

(defn deps
  "Installs project dependencies"
  []
  (println "Installing Dependencies")
  (lein/resolve-and-apply (project) ["deps"]))

(defn docs
  "Rebuilds documentation against the latest codebase"
  []
  (println "Building docs")
  (lein/resolve-and-apply (project) ["codox"]))

(shuck/with-print-out
  (apply prospect/run *ns* *command-line-args*)
  (shuck/done))
