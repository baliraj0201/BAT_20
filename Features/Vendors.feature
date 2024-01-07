Feature:Vendor

@reg
Scenario Outline: Search vendor
 Given  User Lanch Chrome Browser
  When   User open url "https://admin-demo.nopcommerce.com/login"
  And   User enter Email as "<email>" and password as "<password>"
  And   User click on Login button
  And  User click on vendor item
  Then User can view vendor page
  When User enter Vendor name as "<Vendorname>" and password as "<Vendoremail>"
  And  User click on Search button
  And close browser
  
  Examples:
   |email|password|Vendorname|Vendoremail|
   |admin@yourstore.com|admin|Vendor 1|vendor1email@gmail.com|
   |admin@yourstore.com|admin|Vendor 2|vendor2email@gmail.com|