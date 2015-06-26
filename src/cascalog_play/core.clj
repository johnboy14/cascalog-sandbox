(ns cascalog-play.core
  (:require
            [cascalog.playground :refer :all]))

(bootstrap)
(?<- (stdout)
       [?person]
       (age ?person 25))

;lets query all users who are aged 25
(?<- (stdout)
     [?person]
     (age ?person 25))

;lets do the same query but return there age also
(?<- (stdout)
     [?person ?age]
     (age ?person ?age)
     (= ?age 25))

;lets query all users under the age of 30
(?<- (stdout)
     [?person ?age]
     (age ?person ?age)
     (< ?age 30))

;lets query all users that follow Emily
(?<- (stdout)
     [?person]
     (follows "emily" ?person))

;lets query all user that follow Emily and are under 30
(?<- (stdout)
     [?person ?age]
     (follows "emily" ?person)
     (age ?person ?age)
     (< ?age 30))

;query users who are following younger people
(?<- (stdout)
     [?person1 ?person2]
     (age ?person1 ?age1)
     (follows ?person1 ?person2)
     (age ?person2 ?age2)
     (< ?age2 ?age1))

;lets emit the age difference aswell
(?<- (stdout)
     [?person1 ?person2 ?diff]
     (age ?person1 ?age1)
     (follows ?person1 ?person2)
     (age ?person2 ?age2)
     (- ?age2 ?age1 :> ?diff); :> bounds the result of (- age2 age1) into ?diff
     (< ?diff 0))

;Aggregators
;lets find the number of people under 30 yrs old
(?<- (stdout)
     [?count]
     (age _ ?a)
     (< ?a 30)
     (c/count ?count))

;lets find the total number of people each user follows
(?<- (stdout)
     [?person ?count]
     (follows ?person _)
     (c/count ?count))

;let's get the average age of people living in a country by combining a count and a sum
(?<- (stdout) [?country ?avg]
     (location ?person ?country _ _) (age ?person ?age)
     (c/count ?count) (c/sum ?age :> ?sum)
     (div ?sum ?count :> ?avg))

(defmapcatop split [sentence]
             (seq (.split sentence "\\s+")))

;lets count the number of times each word appears in a sentence
(?<- (stdout) [?word ?count]
     (sentence ?s)
     (split ?s :> ?word)
     (c/count ?count))
;lets count the number of times the word 'the' appears in the sentence
(?<- (stdout) [?word ?count]
     (sentence ?s)
     (split ?s :> ?word)
     (= ?word "the")
     (c/count ?count))

;left join
(?<- (stdout) [?person ?age ?gender]
     (age ?person ?age) (gender ?person ?gender))

;full outer join including null values
(?<- (stdout) [?person !!age !!gender]
     (age ?person !!age) (gender ?person !!gender))
