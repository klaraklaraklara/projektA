package cz.vutbr.feec.utko.bpcmds.projektA;

import org.jcodec.api.JCodecException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WebController {
    @Autowired
    private ProjectResourceComponent handler;

    private final static String DASH_DIRECOTRY = "D:\\VUT\\MPEG\\MPEG-DASH\\";

    private final static String suffix = "mpd";

    @RequestMapping(value = "/dash/{file}", method = RequestMethod.GET)
    public void streming(@PathVariable("file") String file,
                         HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        File STREAM_FILE = new File(DASH_DIRECOTRY + file);
        request.setAttribute(ProjectResourceComponent.ATTR_FILE, STREAM_FILE);
        handler.handleRequest(request, response);
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/videocollection")
    public ModelAndView videoCollection() throws IOException, JCodecException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName = "videocollection";

        VideoLibrary library = new VideoLibrary();
        Collection<File> files = library.getFiles(DASH_DIRECOTRY, suffix);
        List<Video> videos = library.getVideoNames(files);

        System.out.println(videos);
        model.put("videoName", videos);

        return new ModelAndView(viewName, model);
    }

    @RequestMapping(value = {"/player/{name}"}, method = RequestMethod.GET)
    public ModelAndView player(@PathVariable("name") String name) throws IOException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName = "player";

        model.put("videoName", name);
        return new ModelAndView(viewName, model);
    }
}
