**The information on this page is outdated; the Symja project has moved to [BitBucket.org](https://bitbucket.org/axelclk/symja_android_library).**



# The user interface #
This is a screenshot of the [Symja online calculator](http://mobmath.appspot.com/):

![http://symja.googlecode.com/svn/wiki/SymjaAjaxGUI.png](http://symja.googlecode.com/svn/wiki/SymjaAjaxGUI.png)

## Common evaluation buttons ##
The following buttons are always defined in the user interface:
  * **Sym** - symbolic evaluation
  * **Num** - numeric evaluation
  * **C** - clears the input text area

## Persisting formulas ##
Through the **Login** link users can sign in with their Google User Account.
When logged in, it's possible to persist user defined formulas and constants with the so-called _$ user symbols_ in the [Google App Engine](http://en.wikipedia.org/wiki/Google_App_Engine) datastore.

You can for example create your own rules or formulas for a new defined symbol `$cube`. Type the following rules in the input area and press the **Sym** button to define your rules:
```
 SetAttributes[$cube,Listable];
 $cube[x_]:=x^3
```

Now you can use this rule by evaluating a symbolic argument
```
 $cube[a] 
```
and you get the result `a^3` in the output area.

Because the `$cube` symbol has the attribute `Listable` you can also evaluate a list of numbers like this
```
 $cube[{1,2,3,4,5}] 
```
and you get the result `{1,8,27,64,125`}.

In the same way you can define constants:
```
 $theAnswerToAllQuestions = 42 
```

By evaluating
```
 $theAnswerToAllQuestions 
```
you get the defined value `42`.

If you are logged in again at a later date, you can reuse the _$ user symbols_ without redefining them.

## Buttons for logged in users ##
The following buttons are **only** defined in the user interface, if you are logged in with your Google Account:
  * **Def** - shows the definition of the _$ user symbol_ that the user has typed into the input area (i.e. the _$ user symbol_ **$cube** used in the example above, will show the defined rules for **$cube** in the output area).
  * **Vars** - shows all defined user variables from the [Google App Engine](http://en.wikipedia.org/wiki/Google_App_Engine) datastore. At the moment a user can define up to 100 _$ user symbol_ formulas.

# Advanced configuration #

## AJAX Evaluation API ##
The simplified online **[Eval API](http://symjaweb.appspot.com/)** lets you dynamically generate calculation forms. To see the Eval API in action, open up a browser window and copy the following URL into it:
  * `http://symjaweb.appspot.com/?ci=x1:X:i:10|y1:Y:i:2&ca=x!:x1!|Fibonacci[x]:Fibonacci[x1]|Binomial[x,y]:Binomial[x1,y1]`

On testiphone.com you can try the same example online in an [iPhone simulator](http://www.testiphone.com/?view=hor&url=http%3A%2F%2Fsymjaweb.appspot.com%2F%3Fci%3Dx1%3AX%3Ai%3A10%7Cy1%3AY%3Ai%3A2%26ca%3Dx%21%3Ax1%21%7CFibonacci%5Bx%5D%3AFibonacci%5Bx1%5D%7CBinomial%5Bx%2Cy%5D%3ABinomial%5Bx1%2Cy1%5D) (you should use the Safari browser to get the real experience).

Here is a link for the general [iPhone AJAX interface](http://www.testiphone.com/?view=hor&url=http%3A%2F%2Fsymjaweb.appspot.com%2F)

The corresponding JSP file for the Evaluation API is the **index.jsp** file

## Install additional packages ##
Symja is extendable through the Package[.md](.md) command. If you've installed AJAXSymja on Google Appengine, you can add new packages through the **admin.jsp** interface. Only users which are administrators for the Google Appengine application are allowed to use the **admin.jsp** interface.

Example package for generating Laguerre or Legendre polynomials (**Note** - public symbols have to start with an uppercase character):
```
Package[ 
  "Polynomials", 
  (* define the public available symbols *)
  {LaguerreP, LegendreP}, 
{ 
  (* Laguerre polynomials 
     http://en.wikipedia.org/wiki/Laguerre_polynomials *) 
  LaguerreP[0,x_]:=1, 
  LaguerreP[1,x_]:=1-x, 
  LaguerreP[n_IntegerQ,x_]:= 
      ExpandAll[(2*n-1-x)*LaguerreP[n-1,x] - (n-1)^2*LaguerreP[n-2,x]] /; NonNegative[n], 
  (* Legendre polynomials 
     http://en.wikipedia.org/wiki/Legendre_polynomials *)
  LegendreP[n_IntegerQ,x_]:=
      1/(2^n)*Sum[ExpandAll[Binomial[n,k]^2*(x-1)^(n-k)*(x+1)^k], {k,0,n}] /; NonNegative[n]
} ]
```

After installing the package with the **Save package** button the new command
```
  LegendreP[7,x]
```
can be evaluated.

# Google Appengine Development #
Before starting, please read the [Google Appengine for Java Introduction](http://code.google.com/appengine/docs/java/gettingstarted/)

Install the Google Plugin for Eclipse as described here:
  * [Using the Google Plugin for Eclipse](http://code.google.com/intl/de-DE/appengine/docs/java/tools/eclipse.html)

Checkout the **symja.ajax** source code from SVN:
  * [svn/trunk/symja.ajax](http://code.google.com/p/symja/source/browse/#svn/trunk/symja.ajax)

Change the **symja.ajax** configuration:
  * open: symja.ajax/war/WEB-INF/appengine-web.xml
  * change the **your application ID** to the ID you configured in your Google Appengine Account
  * modify the other configuration options as desired


Select menu _Run->Run As->Web Application_ to run the Google Web Application locally.