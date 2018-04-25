(ns tickle.core)

(defn demangle [func]
  (clojure.string/replace (second (re-find #"^.+\$(.+)\@.+$" (str func)))
                          #"\_QMARK\_" "?"))

(defn predicate->spec [& {:keys [predicate error-fn]
                          :or {error-fn (fn [data] (str data " is not " (demangle predicate)))}}]
  (fn [data]
    (if (predicate data)
      :valid
      {:error (error-fn data)})))

(defn show [data]
  (str (pr-str (type data)) ": " (pr-str data)))

(def string
  (predicate->spec
   :predicate string?
   :error-fn (fn [data] (str (show data) " is not a string"))))

(def number
  (predicate->spec
    :predicate number?
    :error-fn (fn [data] (str (show data) " is not a number"))))
 
(defn valid? [spec data]
  (= :valid (spec data)))

(defn explain [spec data]
  (let [result (spec data)]
    (if (= :valid result) "Valid" (:error result))))
