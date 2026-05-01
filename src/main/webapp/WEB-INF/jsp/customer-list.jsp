<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customers List</title>
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
        .container { max-width: 1100px; margin: 0 auto; }
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
            background: linear-gradient(135deg, #667eea, #764ba2);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            background-clip: text;
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
            background: linear-gradient(135deg, #667eea, #764ba2);
            color: #fff;
        }
        .btn-primary:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(102,126,234,0.4);
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
            overflow: hidden;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        thead th {
            background: rgba(255,255,255,0.08);
            padding: 16px 20px;
            text-align: left;
            font-weight: 600;
            font-size: 0.85rem;
            text-transform: uppercase;
            letter-spacing: 0.5px;
            color: rgba(255,255,255,0.7);
            border-bottom: 1px solid rgba(255,255,255,0.1);
        }
        tbody td {
            padding: 14px 20px;
            border-bottom: 1px solid rgba(255,255,255,0.05);
            font-size: 0.9rem;
        }
        tbody tr {
            transition: background 0.2s ease;
        }
        tbody tr:hover {
            background: rgba(255,255,255,0.04);
        }
        tbody tr:last-child td {
            border-bottom: none;
        }
        .id-badge {
            background: rgba(102,126,234,0.2);
            color: #667eea;
            padding: 4px 10px;
            border-radius: 6px;
            font-weight: 600;
            font-size: 0.8rem;
        }
        .email-text { color: rgba(255,255,255,0.6); }
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
            <h1>👥 Customers</h1>
            <div class="nav-links">
                <a href="${pageContext.request.contextPath}/" class="btn btn-secondary">🏠 Home</a>
                <a href="${pageContext.request.contextPath}/customers/add" class="btn btn-primary">+ Add Customer</a>
            </div>
        </div>

        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">✅ ${successMessage}</div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-error">❌ ${errorMessage}</div>
        </c:if>

        <c:choose>
            <c:when test="${not empty customers}">
                <div class="table-wrapper">
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Address</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="customer" items="${customers}">
                                <tr>
                                    <td><span class="id-badge">#${customer.id}</span></td>
                                    <td><strong>${customer.name}</strong></td>
                                    <td class="email-text">${customer.email}</td>
                                    <td>${customer.phone}</td>
                                    <td>${customer.address}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/customers/edit/${customer.id}" class="btn btn-edit">✏️ Edit</a>
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
                        <h3>No customers found</h3>
                        <p>Add your first customer to get started.</p>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
