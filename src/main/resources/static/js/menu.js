/**
 * Clear all active class for <ul> menu
 *
 * @param menuId <ul> menu identifier
 */
function clearAllActiveElements(menuId) {
    $(menuId).find('li').each(function () {
        $(this).removeClass('active');
    });
}

/**
 * Setting up active class for <li> element
 *
 * @param elementId activate this element menu
 */
function setMenuActiveElement(elementId) {
    // Main menu
    clearAllActiveElements('#menu');
    $(elementId).addClass('active');
}