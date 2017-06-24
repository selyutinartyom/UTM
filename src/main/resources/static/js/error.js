/**
 * Add error message to collapse <div> with setting up in class
 *
 * @param elementId collapse <div> identifier
 * @param errorMessageId paragraph identifier
 * @param message error message text
 */
function addError(elementId, errorMessageId, message) {
    $(errorMessageId).text(message);
    $(elementId)
        .addClass('in')
        .attr("aria-expanded", "true")
        .removeAttr("style");
}