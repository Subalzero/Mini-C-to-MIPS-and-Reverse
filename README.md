# Mini-C-to-MIPS-and-Reverse
Full Program with GUI

Use eclipse ide then compile.

Grammar rules for MIPS input:

    program -> stmts

    stmts -> stmt stmts

    stmt -> label: stmts |

        li  reg, reg, num |
        
        addi  reg, reg, num |
        
        andi  reg, reg, num |
        
        ori  reg, reg, num |
        
        xori  reg, reg, num |
        
        add  reg, reg, reg |
        
        sub  reg, reg, reg |
        
        mult reg, reg, reg |
        
        div  reg, reg, reg |
        
        and  reg, reg, reg |
        
        or  reg, reg, reg |
        
        xor  reg, reg, reg |
        
        j label
        
 Grammar rules for Mini C is based from the Dragon book.
 
 Refer 'Compilers: Principles, Techniques, and Tools'
