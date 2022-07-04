(require '[clojure.repl :as repl])

;; Literals

42                       ; integer
-1.5                     ; floating point
22/7                     ; ratio
4252627298291N           ; arbitrary precision integer
72627282.5272839303M     ; arbitrary precision
##Inf                    ; positive infinity
##-Inf                   ; negative infinity
##NaN                    ; not a number

"hello"                  ; string
\e                       ; character
#"[0-9]+"                ; regular expression
\u5423                   ; Unicode character 
\o226                    ; Unicode character in octal value 

map                      ; symbol
+                        ; symbol - most punctuation allowed
clojure.core/+           ; namespaced symbol
nil                      ; null value
true false               ; booleans
:alpha                   ; keyword
:release/alpha           ; keyword with namespace

;; Function invocation
(+ 3 4)                      ; addition
(println "Hello World")      ; display string and return nil -- side effect
(prn "Hello World")          ; display string data and return data

;; Repl goto functions
(repl/doc +)                 ; documentation of the `+` function
(repl/doc repl/doc)          ; documentation of the `doc` function
(repl/apropos "+")           ; find functions that match a string or regex
(repl/find-doc "trim")       ; search into documentation string
(repl/dir clojure.repl)      ; list all function in namespace
(repl/source repl/dir)       ; show the source of a function

(def x 7)                    ; bind the symbol x to the value 7 - this is called a var
(println x)                  ; print the he value of x

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;Exercice 1
; compute the sum of 7654 and 1234

; Rewrite the following algebraic expression as a Clojure expression: ( 7 + 3 * 4 + 5 ) / 10

; Using REPL documentation functions, find the documentation for the rem and mod functions. Compare the results of the provided expressions based on the documentation

; Using find-doc, find the function that prints the stack trace of the most recent REPL exception.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;    name   params         body
;;    -----  ------  -------------------
(defn greet  [name]  (str "Hello, " name))           ;; define a function "greet" with one argument "name"

(greet "students")                                   ;; invoke function "greet"

(defn messenger                                      ;; multi-arity function definition
  ([]     (messenger "Hello world!"))                ;; first definition
  ([msg]  (println msg)))                            ;; second definition

(messenger)                                          ;; invoke the first definition
(messenger "Hello class!")                           ;; invoke the second definition

(defn hi_guys [& xs] (println xs))                   ;; multi-variadic function
(hi_guys "foo")
(hi_guys "foo" "bar")
(hi_guys "foo" "bar" "baz")

(fn  [message]  (println message))                   ;; anonymous function - not bound to any symbol

;; exemple of a rich comment
(comment
  (defn greeting [name] (str "Hello, " name))        ;; the two definitions are equivalent
  (def  greeting (fn [name] (str "Hello, " name))))

#(+ 6 %)                                             ;; Equivalent to: (fn [x] (+ 6 x))
#(+ %1 %2)                                           ;; Equivalent to: (fn [x y] (+ x y))
#(println %1 %2 %&)                                  ;; Equivalent to: (fn [x y & zs] (println x y zs))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Exercice 2
; 1) Define a function greet that takes no arguments and prints "Hello". Replace the ___ with the implementation: (defn greet [] _)

; 2) Redefine greet using def, first with the fn special form and then with the #() reader macro.
;; using fn
;(def greet __)
;; using #()
;(def greet __)

; 3) Define a function greeting which:
; Given no arguments, returns "Hello, World!"
; Given one argument x, returns "Hello, x!"
; Given two arguments x and y, returns "x, y!"
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(get ["abc" false 99] 0)                        ;; Retrieve element at index 0
(get ["abc" false 99] 1)                        ;; Retrieve element at index 1
(get ["abc" false 99] 14)                       ;; Retrieve element at index 14 - return nil

(count [1 2 3])                                 ;; count the number of elements of the vector 
(vector 1 2 3)                                  ;; create a vector - equivalent to [1 2 3]
(conj [1 2 3] 4 5 6)                            ;; return a new vector with new element "added"

(def v [1 2 3])
(conj v 4 5 6)                                  ;; conj[oin] vector v with new values 
(println v) ;; print the value of v

(def players #{"Fred" "Kelly" "Frank" "Alice"}) ;; declare the set "players"
(conj players "Alfred" "Bob")
(disj players "Alice" "Gordon")
(contains? players "Kelly")                     ;; check if the set contains Kelly
(contains? players "Bob")                       ;; check if the set contains Bob

(def new-players ["Tim" "Sue" "Greg"])          ;; Create a new vector and bind it to new-players
(into players new-players)                      ;; Merge the content of players and new-players in a new vector

(def scores {"Fred"   1400
             "Bob"    1240
             "Angela" 1024})                    ;; Create a new map

(assoc scores "Sally" 0)                        ;; Return a new map with a new entry: Sally as key and 0 as value
(assoc scores "Bob" 0)
scores
(dissoc scores "Bob")                           ;; Return a new map with the key "Bob" removed

(def person
  {:first-name "Kelly"
   :last-name "Keen"
   :age 32
   :occupation "Programmer"})                   ;; Create new map - Note that keywords are used here
(get person :occupation)                        ;; Get the value of the key :occupation
(person :occupation)                            ;; Get the value of the key :occupation - maps in clojure implement IFn
(:occupation person)                            ;; Get the value of the key :occupation - keywords implements IFn

(def company
  {:name "WidgetCo"
   :address {:street "123 Main St"
             :city "Springfield"
             :state "IL"}})
(get-in company [:address :city])                         ;; Retrieve the value of ::address => :city in the company map
(assoc-in company [:address :street] "303 Broadway")      ;; Return new map with with :address => :street modified

(str "2 is " (if (even? 2) "even" "odd"))                 ;; condition - if is an expression
(if (true? false) "impossible!")                          ;; else is optional

(if (even? 5)
  (do (println "even")                                    ;; Use do when the branch do multiple things before returning a value
      true)
  (do (println "odd")
      false))

(when (neg? x)                                                         ;; when is equivalent to if without the else clause
  (throw (RuntimeException. (str "x must be positive: " x))))

(dotimes [i 3]                                                         ;; Iteration
  (println i))

(doseq [letter [:a :b]                                                 ;; Repeatedly execute the body of the loop;
        number (range 3)] ; list of 0, 1, 2
  (prn [letter number]))                                               ;; Processes all permutations of sequence content [:a 0] [:a 1] [:a 2] etc

(for [letter [:a :b]                                                   ;; List comprehension, not a for-loop - return a value
      number (range 3)] ; list of 0, 1, 2
  [letter number])