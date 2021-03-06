package fdm 

/**
  * There are several types in Scala that have special meaning.
  */
object special_types:
  /**
   * EXERCISE 1
   * 
   * Find a type existing in the Scala standard library, which we will call `One`, which has a 
   * single "inhabitant" (i.e. there exists a single unique value that has this type).
   */
  type One = TODO 

  /**
   * EXERCISE 2
   * 
   * Find a type existing in the Scala standard library, which we will call `Zero`, which has no 
   * "inhabitants" (i.e. there exists no values of this type).
   */
  type Zero = TODO

  /**
    * EXERCISE 3
    * 
    * Scala allows you to treat `Nothing` as any other type. To demonstrate this to yourself, 
    * change the return type of this function to whatever type you like, then try to explain why 
    * this rule in the Scala compiler will not lead to any crashes of your application.
    */
    def nothingIsAnything(value: Nothing): Nothing = value

/**
 * Data types that are formed from case classes (products) and enums (sums) are sometimes called 
 * _algebraic data types_. The word _algebraic_ here refers to the _algebraic laws_ that are 
 * satisfied by type-level operators that compose types.
 */
object algebra:
  /**
   * EXERCISE 3
   * 
   * If we use `*` to denote product composition of types, then as with ordinary multiplication on 
   * numbers, we should have that `A * B` is the same as `B * A`.
   * 
   * Although the tuples (A, B) and (B, A) are not exactly the same, they are equivalent because 
   * they store the same amount of information. For example, (Boolean, String) stores the same 
   * amount of information as (String, Boolean). In math and in functional programming, this 
   * equivalence is called an "isomorphism", and it can be regarded as a weaker but more useful 
   * definition of equality.
   */
  def toBA[A, B](ab: (A, B)): (B, A) = TODO 
  def toAB[A, B](ba: (B, A)): (A, B) = TODO 

  def roundtripAB[A, B](t: (A, B)): (A, B) = toAB(toBA(t))
  def roundtripBA[A, B](t: (B, A)): (B, A) = toBA(toAB(t))

  /**
   * EXERCISE 4
   * 
   * If we use `+` to sum product composition of types, then as with ordinary addition on 
   * numbers, we should have that `A + B` is the same as `B + A`.
   * 
   * Although the eithers Either[A, B] and Either[B, A] are not exactly the s;ame, they are 
   * isomorphic, as with tuples.
   */
  def toBA[A, B](ab: Either[A, B]): Either[B, A] = TODO 
  def toAB[A, B](ba: Either[B, A]): Either[A, B] = TODO 

  def roundtripAB[A, B](t: Either[A, B]): Either[A, B] = toAB(toBA(t))
  def roundtripBA[A, B](t: Either[B, A]): Either[B, A] = toBA(toAB(t))

  /**
   * EXERCISE 5
   * 
   * As with multiplication of numbers, we also have `A * 1` is the same as `A`.
   */
  def withUnit[A](v: A): (A, Unit) = TODO 
  def withoutUnit[A](v: (A, Unit)): A = TODO 

  def roundtripUnit1[A](v: A): A = withoutUnit(withUnit(v))
  def roundtripUnit2[A](t: (A, Unit)): (A, Unit) = withUnit(withoutUnit(t))

  /**
   * EXERCISE 6
   * 
   * As with multiplication of numbers, we also have `A + 0` is the same as `A`.
   */
  def withNothing[A](v: A): Either[A, Nothing] = TODO 
  def withoutNothing[A](v: Either[A, Nothing]): A = TODO 

  def roundtripNothing1[A](v: A): A = withoutNothing(withNothing(v))
  def roundtripNothing2[A](t: Either[A, Nothing]): Either[A, Nothing] = withNothing(withoutNothing(t))

  /**
   * EXERCISE 7
   * 
   * As with multiplication of numbers, we also have `A * 0` is the same as `0`.
   */
  def withValue[A](v: Nothing): (A, Nothing) = TODO 
  def withoutValue[A](v: (A, Nothing)): Nothing = TODO

  def roundtripValue1(v: Nothing): Nothing = withoutValue(withValue(v))
  def roundtripValue2[A](t: (A, Nothing)): (A, Nothing) = withValue(withoutValue(t))

  /**
   * EXERCISE 8
   * 
   * All functional data types can be written using sums and products, and therefore have an 
   * algebraic definition. For recursive data types, this can be an infinite definition. For 
   * example, the type of `List[A]` can be defined as `1 + A + A * A + A * A * A + ...`, or,
   * by using exponentiation, `1 + A + A^2 + A^3 + ...`.
   * 
   * Find the algebraic definition for the following type `Tree`.
   */
  enum Tree[+A]:
    case Leaf(value: A)
    case Fork(left: Tree[A], right: Tree[A])