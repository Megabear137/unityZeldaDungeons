using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DoorUpScript : MonoBehaviour
{

    int doorState;
    int NONE;
    int CLOSED;
    int OPEN;
    int LOCKED;
    Animator animator;
    BoxCollider2D collider;

    // Start is called before the first frame update
    void Start()
    {
        NONE = 0;
        CLOSED = 1;
        OPEN = 2;
        LOCKED = 3;

        doorState = OPEN;

        animator = GetComponent<Animator>();
        collider = GetComponent<BoxCollider2D>();
    }

    // Update is called once per frame
    void Update()
    {
        animator.SetInteger("DoorState", doorState);

        if(doorState == OPEN){
            collider.enabled = false;
        }
    }
}
