package io.secundus.model

/**
 * Local XML file
 *
 * @author Secundus
 * @since 31.03.2017 0:40
 */
class LocalFile(var name: String,
                var path: String,
                var type: DocumentType)

enum class DocumentType {
    OUT, IN
}