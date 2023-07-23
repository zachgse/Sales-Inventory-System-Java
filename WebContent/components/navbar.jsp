<nav class="navbar navbar-expand-lg bg-body-tertiary py-3">
  <div class="container">
    <a class="navbar-brand" href="index.jsp">Boy Kahang</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ms-auto mb-2 mb-lg-0 mt-2">
         <li class="nav-item dropdown mx-2">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" id="nav-item">
           	Inventory Management System
          </a>
          <ul class="dropdown-menu text-center">
          	<li>
            	<a class="nav-link active" href="<%= request.getContextPath() %>/inventory" style="color:black;">
            		View Items
            	</a>
            </li>
            <li><hr class="dropdown-divider"></li>
            <li>
            	<a class="nav-link active" href="<%= request.getContextPath() %>/InventoryCreate" style="color:black;">
            		Add Item
            	</a>
            </li>
            <li><hr class="dropdown-divider"></li>
            <li>
            	<a class="nav-link active" href="<%= request.getContextPath() %>/InventoryHistoryServlet" style="color:black;">
            		History
            	</a>
            </li>
          </ul>
        </li>
        <li class="nav-item dropdown mx-2">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" id="nav-item">
           	Sales
          </a>
          <ul class="dropdown-menu text-center">
          	<li>
            	<a class="nav-link active" href="<%= request.getContextPath() %>/SalesServlet" style="color:black;">
            		View Sales
            	</a>
            </li>
            <li><hr class="dropdown-divider"></li>
            <li>
            	<a class="nav-link active" href="<%= request.getContextPath() %>/SalesCreateServlet" style="color:black;">
            		Add Sales
            	</a>
            </li>
          </ul>
        </li>      
      </ul>
    </div>
  </div>
</nav>

