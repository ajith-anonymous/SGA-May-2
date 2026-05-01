<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Orders List</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body {
            font-family: 'Inter', sans-serif;
            background: linear-gradient(135deg, #0f0c29 0%, #302b63 50%, #24243e 100%);
            min-height: 100vh;
            color: #fff;
            padding: 40px 20px;
        }
        .container { max-width: 1200px; margin: 0 auto; }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
            flex-wrap: wrap;
            gap: 15px;
        }
        .header h1 {
            font-size: 2rem;
            font-weight: 700;
            background: linear-gradient(135deg, #f093fb, #f5576c);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            background-clip: text;
        }
        .badge-info {
            display: inline-block;
            background: rgba(102,126,234,0.2);
            color: #a3bffa;
            padding: 6px 14px;
            border-radius: 8px;
            font-size: 0.8rem;
            font-weight: 500;
            margin-bottom: 20px;
        }
        .btn {
            display: inline-flex;
            align-items: center;
            gap: 8px;
            padding: 12px 24px;
            border-radius: 12px;
            font-weight: 600;
            font-size: 0.9rem;
            text-decoration: none;
            transition: all 0.3s ease;
            border: none;
            cursor: pointer;
        }
        .btn-primary {
            background: linear-gradient(135deg, #f093fb, #f5576c);
            color: #fff;
        }
        .btn-primary:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(240,147,251,0.4);
        }
        .btn-secondary {
            background: rgba(255,255,255,0.1);
            color: #fff;
            border: 1px solid rgba(255,255,255,0.2);
        }
        .btn-secondary:hover {
            background: rgba(255,255,255,0.15);
            transform: translateY(-2px);
        }
        .btn-edit {
            background: linear-gradient(135deg, #4fd1c5, #38b2ac);
            color: #fff;
            padding: 8px 18px;
            font-size: 0.85rem;
        }
        .btn-edit:hover {
            box-shadow: 0 5px 15px rgba(79,209,197,0.4);
            transform: translateY(-2px);
        }
        .alert {
            padding: 14px 20px;
            border-radius: 12px;
            margin-bottom: 20px;
            font-size: 0.9rem;
            animation: slideDown 0.4s ease;
        }
        @keyframes slideDown {
            from { opacity: 0; transform: translateY(-10px); }
            to { opacity: 1; transform: translateY(0); }
        }
        .alert-success {
            background: rgba(72,187,120,0.15);
            border: 1px solid rgba(72,187,120,0.3);
            color: #68d391;
        }
        .alert-error {
            background: rgba(245,101,101,0.15);
            border: 1px solid rgba(245,101,101,0.3);
            color: #fc8181;
        }
        .table-wrapper {
            background: rgba(255,255,255,0.06);
            backdrop-filter: blur(20px);
            border: 1px solid rgba(255,255,255,0.1);
            border-radius: 16px;
            overflow-x: auto;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            min-width: 900px;
        }
        thead th {
            background: rgba(255,255,255,0.08);
            padding: 16px 18px;
            text-align: left;
            font-weight: 600;
            font-size: 0.82rem;
            text-transform: uppercase;
            letter-spacing: 0.5px;
            color: rgba(255,255,255,0.7);
            border-bottom: 1px solid rgba(255,255,255,0.1);
        }
        tbody td {
            padding: 14px 18px;
            border-bottom: 1px solid rgba(255,255,255,0.05);
            font-size: 0.88rem;
        }
        tbody tr {
            transition: background 0.2s ease;
        }
        tbody tr:hover {
            background: rgba(255,255,255,0.04);
        }
        tbody tr:last-child td { border-bottom: none; }
        .id-badge {
            background: rgba(240,147,251,0.2);
            color: #f093fb;
            padding: 4px 10px;
            border-radius: 6px;
            font-weight: 600;
            font-size: 0.8rem;
        }
        .customer-name {
            background: rgba(102,126,234,0.15);
            color: #a3bffa;
            padding: 4px 10px;
            border-radius: 6px;
            font-weight: 500;
            font-size: 0.82rem;
        }
        .status {
            padding: 5px 12px;
            border-radius: 8px;
            font-size: 0.8rem;
            font-weight: 600;
        }
        .status-delivered {
            background: rgba(72,187,120,0.15);
            color: #68d391;
        }
        .status-shipped {
            background: rgba(237,198,68,0.15);
            color: #ecc94b;
        }
        .status-processing {
            background: rgba(102,126,234,0.15);
            color: #a3bffa;
        }
        .price { color: #68d391; font-weight: 600; }
        .empty-state {
            text-align: center;
            padding: 60px 20px;
            color: rgba(255,255,255,0.4);
        }
        .empty-state .icon { font-size: 3rem; margin-bottom: 15px; }
        .nav-links { display: flex; gap: 10px; }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>📦 Orders</h1>
            <div class="nav-links">
                <a href="${pageContext.request.contextPath}/" class="btn btn-secondary">🏠 Home</a>
                <a href="${pageContext.request.contextPath}/orders/add" class="btn btn-primary">+ Add Order</a>
            </div>
        </div>

        <div class="badge-info">📊 Data fetched using INNER JOIN between Orders & Customers</div>

        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">✅ ${successMessage}</div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-error">❌ ${errorMessage}</div>
        </c:if>

        <c:choose>
            <c:when test="${not empty orders}">
                <div class="table-wrapper">
                    <table>
                        <thead>
                            <tr>
                                <th>Order ID</th>
                                <th>Product</th>
                                <th>Qty</th>
                                <th>Total Price</th>
                                <th>Order Date</th>
                                <th>Status</th>
                                <th>Customer</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="order" items="${orders}">
                                <tr>
                                    <td><span class="id-badge">#${order.id}</span></td>
                                    <td><strong>${order.productName}</strong></td>
                                    <td>${order.quantity}</td>
                                    <td class="price">₹${order.totalPrice}</td>
                                    <td>${order.orderDate}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${order.status == 'Delivered'}">
                                                <span class="status status-delivered">${order.status}</span>
                                            </c:when>
                                            <c:when test="${order.status == 'Shipped'}">
                                                <span class="status status-shipped">${order.status}</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="status status-processing">${order.status}</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td><span class="customer-name">${order.customer.name}</span></td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/orders/edit/${order.id}" class="btn btn-edit">✏️ Edit</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <div class="table-wrapper">
                    <div class="empty-state">
                        <div class="icon">📭</div>
                        <h3>No orders found</h3>
                        <p>Create your first order to get started.</p>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
