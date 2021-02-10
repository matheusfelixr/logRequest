
public class RequestTraceFilter extends WebRequestTraceFilter {
	
	private final String[] excludedEndpoints = new String[]{"/css/**", "/js/**", "/trace"};

    RequestTraceFilter(TraceRepository repository, TraceProperties properties) {
        super(repository, properties);
    }

    @Override
    protected boolean shouldNotFilter(final HttpServletRequest request) throws ServletException {
        return Arrays.stream(excludedEndpoints)
                .anyMatch(e -> new AntPathMatcher().match(e, request.getServletPath()));
    }
}
