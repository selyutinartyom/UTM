package io.secundus.mustache

import com.samskivert.mustache.Mustache
import com.samskivert.mustache.Mustache.Compiler
import com.samskivert.mustache.Template.Fragment
import io.secundus.service.UtmService
import io.secundus.model.LocalFile
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import java.io.Writer

/**
 * Controller advice
 *
 * @author Selutin_AV
 * @since 30.03.2017 12:40
 */
@ControllerAdvice
class LayoutAdvice @Autowired constructor(
        val compiler: Compiler,
        val service: UtmService) {

    @ModelAttribute("layout")
    fun layout(model: Map<String, Any>): Mustache.Lambda {
        return Layout(compiler)
    }

    @ModelAttribute("title")
    fun defaults(@ModelAttribute layout: Layout): Mustache.Lambda {
        return Mustache.Lambda { frag, out -> layout.title = frag.execute() }
    }

    @ModelAttribute("userScripts")
    fun userScripts(@ModelAttribute layout: Layout): Mustache.Lambda {
        return Mustache.Lambda { frag, out -> layout.userScripts = frag.execute() }
    }

    @ModelAttribute("files")
    fun listFiles(): Iterable<LocalFile> {
        return service.getFiles()
    }
}

/**
 * Mustache layout
 *
 * @author Secundus
 * @since 30.03.2017 22:22
 */
class Layout(val compiler: Compiler) : Mustache.Lambda {

    var title: String = "UTM"

    lateinit var body: String

    var userScripts: String = ""

    override fun execute(frag: Fragment?, out: Writer?) {
        if (frag != null) {
            body = frag.execute()
            compiler.compile("{{>layout}}").execute(frag.context(), out)
        }
    }
}

/**
 * Page controller
 *
 * @author Secundus
 * @since 30.03.2017 12:40
 */
@Controller
class PageController {
    @GetMapping("/")
    fun home() = "index"
}