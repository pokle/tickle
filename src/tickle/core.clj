(ns tickle.core)

(defn demangle [func]
  (clojure.string/replace (second (re-find #"^.+\$(.+)\@.+$" (str func)))
                          #"\_QMARK\_" "?"))
(defn show [data]
  (str (pr-str (type data)) ": " (pr-str data)))

(defn predicate->spec
  [& {:keys [predicate name error-fn]
      :or {name (demangle predicate)
           error-fn (fn [data] (str data " is not " (demangle predicate)))}}]
  ^{:name name}
  (fn [data]
    (if (predicate data)
      :valid
      {:error (error-fn data)})))

(defn string [data]
  (if (string? data)
    :valid
    {:error (str "Not a string: " (show data))}))

(defn number [data]
  (if (number? data)
    :valid
    {:error (str "Not a number: " (show data))}))
(defn valid-result? [spec-result]
  (= :valid spec-result))

(defn valid? [spec data]
  (= :valid (spec data)))

(defn either [& specs]
  (fn [data]
    (if (some #(valid? % data) specs)
      :valid
      {:error (str "None of the specs (" (String/join "," (map demangle specs))  ") matched " (show data))})))

(defn explain [spec data]
  (let [result (spec data)]
    (if (= :valid result) "Valid" (:error result))))
