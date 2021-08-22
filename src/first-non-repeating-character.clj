(ns first-non-repeating-character
  (:require [clojure.string :as string]))

"aabcc" ;; b
"abc" ;; a
"aabbcc" ;; nil
"aabccd" ;; b

(defn solution-1 [s]
  (let [char-counts (reduce (fn [acc c]
                              (let [current-count (get acc c 0)]
                                (assoc acc c (inc current-count)))) {} s)]
    (first
     (filter #(= (get char-counts %) 1) s))))


(defn solution-2 [s]
  (let [get-index (fn [c] (- (int c) (int \a)))
        char-counts (reduce  (fn [acc c]
                               (let [index (get-index c)
                                     current-count (nth acc index)]
                                 (assoc acc index (inc current-count)))) (vec (repeat 26 0)) s)]
    (first
     (filter #(= (nth char-counts (get-index %)) 1) s))))


(defn solution-3 [s]
  (first
   (filter (fn [c]
             (= (string/index-of s c) (string/last-index-of s c))) s)))

(comment
  (solution-1 "aabbcdef")
  (solution-2 "aabbcdefz")
  (solution-3 "aabbcdefz"))

