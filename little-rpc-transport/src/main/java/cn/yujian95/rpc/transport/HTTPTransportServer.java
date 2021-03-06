package cn.yujian95.rpc.transport;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author YuJian95  yujian95_cn@163.com
 * @date 2022/1/17
 */
@Slf4j
public class HTTPTransportServer implements TransportServer {

    private RequestHandler handler;
    private Server server;

    /**
     * 初始化
     *
     * @param port    端口
     * @param handler 请求处理器
     */
    @Override
    public void init(int port, RequestHandler handler) {
        this.server = new Server(port);
        this.handler = handler;

        ServletContextHandler ctx = new ServletContextHandler();
        server.setHandler(ctx);

        ServletHolder holder = new ServletHolder(new RequestServlet());
        ctx.addServlet(holder, "/*");
    }

    /**
     * 启动、监听
     */
    @Override
    public void start() {
        try {
            server.start();
            server.join();
        } catch (InterruptedException e) {
            log.warn(e.getMessage(), e);
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 关闭监听
     */
    @Override
    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 重写 RequestServlet 的 doPost 方法
     */
    class RequestServlet extends HttpServlet {
        /**
         * Called by the server (via the <code>service</code> method)
         * to allow a servlet to handle a POST request.
         * <p>
         * The HTTP POST method allows the client to send
         * data of unlimited length to the Web server a single time
         * and is useful when posting information such as
         * credit card numbers.
         *
         * <p>When overriding this method, read the request data,
         * write the response headers, get the response's writer or output
         * stream object, and finally, write the response data. It's best
         * to include content type and encoding. When using a
         * <code>PrintWriter</code> object to return the response, set the
         * content type before accessing the <code>PrintWriter</code> object.
         *
         * <p>The servlet container must write the headers before committing the
         * response, because in HTTP the headers must be sent before the
         * response body.
         *
         * <p>Where possible, set the Content-Length header (with the
         * {@link ServletResponse#setContentLength} method),
         * to allow the servlet container to use a persistent connection
         * to return its response to the client, improving performance.
         * The content length is automatically set if the entire response fits
         * inside the response buffer.
         *
         * <p>When using HTTP 1.1 chunked encoding (which means that the response
         * has a Transfer-Encoding header), do not set the Content-Length header.
         *
         * <p>This method does not need to be either safe or idempotent.
         * Operations requested through POST can have side effects for
         * which the user can be held accountable, for example,
         * updating stored data or buying items online.
         *
         * <p>If the HTTP POST request is incorrectly formatted,
         * <code>doPost</code> returns an HTTP "Bad Request" message.
         *
         * @param req  an {@link HttpServletRequest} object that
         *             contains the request the client has made
         *             of the servlet
         * @param resp an {@link HttpServletResponse} object that
         *             contains the response the servlet sends
         *             to the client
         * @throws IOException      if an input or output error is
         *                          detected when the servlet handles
         *                          the request
         * @throws ServletException if the request for the POST
         *                          could not be handled
         * @see ServletOutputStream
         * @see ServletResponse#setContentType
         */
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            InputStream in = req.getInputStream();
            OutputStream out = resp.getOutputStream();

            if (handler != null) {
                handler.onRequest(in, out);
            }

            out.flush();

        }
    }
}
