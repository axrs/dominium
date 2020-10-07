(ns io.axrs.dominium.dom
  (:require
    [clojure.string :as str]))

(def ^:private ^:dynamic *self* js/self)

(defn- valid-selector? [v]
  (and (string? v)
       (not (str/blank? v))))

(defn node?
  "True if n is an instance of js/Node"
  [^js n]
  (and (some? n)
       (instance? js/Node n)))

(defn element?
  "True if e is an instance of js/Element"
  [^js e]
  (instance? js/Element e))

(defn find-first
  "Finds the first `Element` within the document that matches the selector or group of selectors.
  `nil` if no matches are found"
  [selector]
  (when (valid-selector? selector)
    (.querySelector *self* selector)))

(defn first-child
  "Returns the `node`'s first child in the tree.
  `nil` if the `node` has no children or is not a `Node`"
  [^js node]
  (when (node? node)
    (.-firstChild node)))

(defn last-child
  "Returns the last child of the `node`.
  `nil` if the `node` has no children or is not a `Node`"
  [^js node]
  (when (node? node)
    (.-lastChild node)))

(defn remove-child
  "Removes the specified `child-node` from the `parent-node`.
  Returns `nil`"
  [^js parent ^js child]
  (when (and (node? parent)
             (node? child))
    (.removeChild parent child)))

(defn remove-children
  "Recursively removes all children `Node`s from the `parent-node`.
  Returns `nil`"
  [^js parent-node]
  (when (node? parent-node)
    (loop [pn parent-node]
      (when-let [last-child (last-child pn)]
        (remove-child pn last-child)
        (recur pn)))))
