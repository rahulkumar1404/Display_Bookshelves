# Automation Script for Urban Ladder

## Problem Statement
Display the name, price of:
1. Bookshelves below Rs. 15000 (including out of stock) with open storage type.
2. First 3 study chairs with highest recommendation.
3. Retrieve all sub-menu items under "Being-At-home" and store in a list.
4. Customize a gift card for "Birthday/Anniversary" and capture the error message for invalid input.

## Key Automation Scope
- Handling alerts, drag & drop, search options.
- Extracting menu items and storing in collections.
- Navigating back to the home page.
- Scrolling down the web page.
- Filling forms on different objects in the web page.
- Capturing warning messages.

## Steps
1. **Bookshelves below Rs. 15000 with Open Storage Type (excluding out of stock)**
   - Navigate to the bookshelves section.
   - Apply filters for price below Rs. 15000 and open storage type.
   - Exclude out of stock items.
   - Display the first three items.

2. **First 3 Study Chairs with Highest Recommendation**
   - Navigate to the study chairs section.
   - Sort by highest recommendation.
   - Display the first three items.

3. **Retrieve Sub-menu Items under "Being-At-home"**
   - Navigate to the "Being-At-home" section.
   - Extract all sub-menu items.
   - Store items in a list and display.

4. **Customize Gift Card and Capture Error Message**
   - Navigate to the gift cards section.
   - Choose "Birthday/Anniversary".
   - Fill in the customization form with an invalid email.
   - Capture and display the error message.

## Example Output
- **Bookshelves**: Hayden Engineered Wood Bookshelf - ₹6,999, Megan Engineered Wood Bookshelf - ₹8,399, Babylon Solid Wood Bookshelf - ₹7,699.
- **Study Chairs**: Galen Study Chair - ₹7,999, Cohen Study Chair - ₹6,999, Edmund Study Chair - ₹7,964.
- **Sub-menu Items**: ["Set up your office with an ergonomic desk & chair", "A hideaway desk", "The perfect partnership", ...]
- **Error Message**: "Please enter a valid email address."
