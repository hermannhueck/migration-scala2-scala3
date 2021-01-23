package migrate2dotty.ana1

// check List of possible anagrams against word
// solution 1: just compares sorted strings

def anagramsFor(word: String, wordsToCheck: List[String]): List[String] =
  wordsToCheck filter { isAnagram(_, word) }

def isAnagram(toCheck: String, word: String): Boolean =
  if toCheck.toLowerCase == word.toLowerCase then
    false
  else
    (lcSorted(toCheck), lcSorted(word)) match
      case (str1, str2) if str1 == str2 => true
      case _                            => false


def lcSorted(s: String) =
  s.toLowerCase.nn.toSeq.sorted.unwrap


import scala.util.chaining._
import scala.language.implicitConversions
import util._
  
@main def Anagrams: Unit =

  line().green pipe println

  // primitive tests

  anagramsFor("Rust", List("Rust"))
    .ensuring(_ == List()) pipe println
  anagramsFor("Rust", List("rust"))
    .ensuring(_ == List()) pipe println
  anagramsFor("Rust", List("rusty"))
    .ensuring(_ == List()) pipe println
  anagramsFor("Rust", List("yrust"))
    .ensuring(_ == List()) pipe println
  anagramsFor("Rust", List("urst", "srut", "surt", "tsru", "tsur", "rust", "trust", "trusty"))
    .ensuring(_ == List("urst", "srut", "surt", "tsru", "tsur")) pipe println

  line().green pipe println
  
end Anagrams
