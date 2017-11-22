package com.epam.cinema.configuration;

public class WebServerInitializer {}
//public class WebServerInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//    private int maxUploadSizeInMb = 5 * 1024 * 1024;
//
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return null;
//    }
//
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return new Class[]{AppConfiguration.class};
//    }
//
//    @Override
//    protected String[] getServletMappings() {
//        return new String[]{"/"};
//    }
//
//    @Override
//    protected DispatcherServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
//        DispatcherServlet ds = new DispatcherServlet(servletAppContext);
//        ds.setThrowExceptionIfNoHandlerFound(true);
//        return ds;
//    }
//
//    @Override
//    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
//        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
//                Paths.get(System.getProperty("java.io.tmpdir")).toString(),
//                maxUploadSizeInMb,
//                maxUploadSizeInMb * 2,
//                maxUploadSizeInMb / 2);
//
//        registration.setMultipartConfig(multipartConfigElement);
//    }
//
//    @Override
//    protected Filter[] getServletFilters() {
//        return new Filter[] {  new DelegatingFilterProxy("springSecurityFilterChain") };
//    }
//}
